/* Jags Rolls Javascript */


/**
 * Text box hilighting
 */

function onFocus() {
	$(this).addClass('focused');
}

function onBlur() {
	$(this).removeClass('focused');
}

function setupTextBoxHilighting(el) {
	$(':text', el).focus(onFocus).blur(onBlur);
}


/**
  Character section
*/

var newCharId = 0;

function nextId() {
    var result = newCharId;
    newCharId += 1;
    return result;
}

function sendAddRoll(type, nameEl) {
    var name = $(nameEl).val();
    $.post(url.add_roll, {
        name: name,
        rollType: type
    });
}

function sendInitRoll(nameEl, reaEl, initEl) {
    var name = $(nameEl).val();
    var rea  = $(reaEl).val();
    var init = $(initEl).val();
    $.post(url.add_init_roll, {
        name: name,
        rea : rea,
        init: init
    });
}

function gmRoll() {
    $.post(url.add_roll, {
        name: '- GM -',
        rollType: 'Roll'
    });
	return false;
}

function addAllInit() {
    var names = [];
    var reas  = [];
    var inits = [];
    $('#character-section li').each(function() {
        var id = this.id.match(/char-(\d+)/)[1];
        var name = $('#name-' + id).val();
        var rea  = $('#rea-' + id).val();
        var init = $('#init-' + id).val();
        names.push(name);
        reas.push(rea);
        inits.push(init);
    });
    $.post(url.add_all_init, {
        names: names.join(','),
        reas : reas.join(','),
        inits: inits.join(',')
    });
    return false;
}

function sendAddReaCost(nameEl, cost) {
    var name = $(nameEl).val();
    $.post(url.add_rea_cost, {
        name: name,
        cost: cost
    });
}

function sendUndoReaCost(nameEl) {
    var name = $(nameEl).val();
    $.post(url.undo_rea_cost, {
        name: name
    });
}

function sendNewRound() {
    $.post(url.clear_round);
    return false;
}

function addButton(el, text, fun, title, css) {
    var button = $('<a href="#">' + text + '</a>');
    el.append(button);
    if (css) button.addClass(css);
    if (title) button.attr('title', title);

    $(button).click(fun);
}

function newCharacter() {
    var id = nextId();
    var ch = $('<li id="char-' + id + '"></li>');
    var form = $('<form></form>');
    var name = $('<input type="text" size="10" id="name-' + id + '"></input>');
    ch.append(form);

    var addLabel = function(name, text) {
        form.append($('<label for="'
                      + name
                      + '-'
                      + id
                      + '">'
                      + text
                      + '</label>'));
    }

    addLabel('name', 'Name');
    form.append(name);

    var makeSend = function(text) {
        addButton(form, text, function() {
            sendAddRoll(text, name);
            return false;
        }, 'Roll the dice');
    }

    makeSend("Roll");
    makeSend("Attack");
    makeSend("Defense");
    makeSend("Damage");

    var makeReaCost = function(val) {
        addButton(form, val, function() {
            sendAddReaCost(name, val);
            return false;
        }, 'Apply REA cost', 'rea-button');
    }

    for (var i = 1; i <= 8; ++i) {
        makeReaCost(i);
    }

    addButton (form, "U", function() {
        sendUndoReaCost(name);
        return false;
    }, 'Undo last REA cost', 'rea-button');

    var init = $('<input type="text" value="10" size="2" id="init-' + id + '"></input>');
    var rea  = $('<input type="text" value="10" size="2" id="rea-' + id + '"></input>');

    addLabel('rea', 'REA');
    form.append(rea);
    addLabel('init', 'Initiative');
    form.append(init);

    addButton(form, "Init", function() {
        sendInitRoll(name,rea,init);
        return false;
    }, 'Roll Initiative', 'init-button');

    addButton(form, "Delete", function() {
        $(ch).remove();
        return false;
    }, 'Delete this character', 'delete-button');
    
    $('#character-section ul').append(ch);
    $(form).submit(function() { return false; });
    setupTextBoxHilighting(ch);
    return false;
}

$(function() {
    $('#add-character').click(newCharacter).click();
    $('#all-init').click(addAllInit);
    $('#gm-roll').click(gmRoll);
    $('#new-round').click(sendNewRound);
});



/*********************************************
 *
 * Comet section
 *
 *
 *
 *********************************************/

var numberOfRolls = 15;
var rollsJson = null;
var initRollsJson = null;

var bigHoverClasses = {
    over: 'sizerOutBig',
    out: 'sizerOverBig'
};

var smallHoverClasses = {
    over: 'sizerOutSmall',
    out: 'sizerOverSmall'
};

function sizerOver() {
    $('#sizer').removeClass('sizer-out-small').removeClass('sizer-out-big')
    .addClass((numberOfRolls > 25) ? 'sizer-over-big' : 'sizer-over-small');
}

function sizerOut() {
    $('#sizer').removeClass('sizer-over-small').removeClass('sizer-over-big')
    .addClass((numberOfRolls > 25) ? 'sizer-out-big' : 'sizer-out-small');
}

$(function () {
    var sizer = $('#sizer');
    sizer.hover(sizerOver, sizerOut);
    sizer.click(function() {
        if (numberOfRolls > 25) {
            numberOfRolls = 15;
        } else {
            numberOfRolls = 99;
        }
        emptyTables();
        redrawTables();
    });
});

function highestRoll(max) {
    return Math.max(max - numberOfRolls, 0);
}

function ifPresent(roll, element) {
    if (roll[element]) {
        return $('<td></td>').text(roll[element]);
    }
    else {
        return $('<td>&nbsp;</td>');
    }
}

function addDice(el, dice) {
    var text = dice.join(',');
    el.attr('title', text);
}

function showRolls() {

    if (rollsJson == null) rollsJson = [];
    
    var div = $('#rolls-left');
    var table = $('<table></table>');
    var header = $('<tr><th>Name</th><th>Action</th><th>Roll</th>'
        + '<th>REA Cost</th></tr>');
    table.append(header);
    var start = highestRoll(rollsJson.length);
    for(var i = start; i < rollsJson.length; ++i) {
        var roll    = rollsJson[i];
        var tr      = $('<tr></tr>');
        var name    = $('<td></td>').text(roll.name);
        var type    = $('<td></td>').text(roll.type);
        var total   = ifPresent(roll, 'roll');
        if (roll.rolls) addDice(total, roll.rolls);
        var reaCost = ifPresent(roll, 'reaCost');;
        tr.append(name).append(type).append(total).append(reaCost);
        table.append(tr);
        if(!roll.thisRound) {
            tr.addClass("old-round");
        }
    }
    div.append(table);
}

function makeToggleCondition(name, type) {
    return function () {
    	var condUrl = this.checked ? url.add_condition : url.remove_condition;
        var param = {
            'name'      : name,
            'condition' : type
        };
        $.post(condUrl, param);
    }
}

function makeDeleteAction(name) {
	return function() {
    	$.post(url.delete_init_roll, {
        	name: name
    	});
    	return false;
	}
}

function showInitRolls() {

    function checkIfNeeded(roll, which) {
        var checkText = '<input type="checkbox"';
        if(roll[which]) {
            checkText += ' checked="checked"';
        }
        checkText += ">";
        checkbox = $(checkText);
        checkbox.click(makeToggleCondition(roll.name, which));
        return $('<td></td>').append(checkbox);
    }
    
    if (!initRollsJson) initRollsJson = [];

    var div = $('#rolls-right');
    var form = $('<form></form>');
    var table = $('<table></table>');
    var header = $('<tr><th>Name</th><th>Roll</th><th>Init</th>'
        + '<th>REA</th><th>Stunned</th><th>Dazed</th>'
        + (isGm ? '<th>Delete</th>' : '')
        + '</tr>');
    table.append(header);
    
    for(var i = 0; i < initRollsJson.length; ++i) {
        var roll  = initRollsJson[i];
        var tr    = $('<tr></tr>');
        var name  = $('<td></td>').text(roll.name);
        var comp = $('<td></td>')
        .text('('
              + roll.init
              + ' - '
              + roll.roll
              + ')');
        addDice(comp, roll.rolls);
        var total = $('<td></td>').text(roll.value);
        var rea = $('<td></td>').text(roll.rea);
        var stunned = checkIfNeeded(roll, "stunned");
        var dazed = checkIfNeeded(roll, "dazed");
        var del;
        if(isGm) {
        	var link = $('<a href="#">d</a>');
        	link.click(makeDeleteAction(roll.name));
        	link.attr('title', 'Delete Init Roll');
        	del = $('<td></td>').append(link);
        } else {
        	del = '';
        }
        
        tr.append(name).append(comp).append(total).append(rea)
            .append(stunned).append(dazed).append(del);
        table.append(tr);
    }
   form.append(table);
   div.append(form);
 }

function emptyTables() {
    $('#rolls-right, #rolls-left').empty();
}

function redrawTables() {
    showRolls();
    showInitRolls();
}


var versionNumber = 0;

function newRolls(json) {
    versionNumber = json.version;
    rollsJson = json.rolls;
    initRollsJson = json.initRolls;
    emptyTables();
    redrawTables();
    cometRequestForRolls();
}

function cometError(err) {
    setTimeout(cometRequestForRolls, 2000);
}

var ONE_MINUTE = 1000 * 60;

function cometRequestForRolls() {
    $.ajax({
        timeout: ONE_MINUTE * 2,
        dataType: 'json',
        data: { version: versionNumber },
        url: url.get_results,
        error: cometError,
        success: newRolls
    });
}

$(function() {
    cometRequestForRolls();
});

/*********************************************
 *
 * Damage Section
 *
 *
 *
 *********************************************/

var chartID = 0;

function nextChartID() {
    var result = "dam-" + chartID;
    chartID += 1;
    return result;
}

function itemID(id, index) {
    return id + '-' + index;
}

var set  = 0;
var mult = 1;
var add  = 2;

var impactDamageChart =
    [['0-' ,   set ,  1],
     ['1'  ,   mult, 0.1],
     ['2-3',   mult, 0.25],
     ['4-5',   mult, 0.33],
     ['6-7',   mult, 0.5],
     ['8-9',   add, -3],
     ['10-11', add, -2],
     ['12',    add, -1],
     ['13-14', mult, 1],
     ['15',    add,  1],
     ['16-17', add,  2],
     ['18-20', add,  3],
     ['21-25', mult, 1.5],
     ['26-29', mult, 1.75],
     ['30+',   mult, 2]];

var penetrationDamageChart =
    [['0-' ,   set ,  1],
     ['1'  ,   mult, 0.1],
     ['2-3',   mult, 0.25],
     ['4-5',   mult, 0.33],
     ['6',     mult, 0.5],
     ['7-8',   add, -3],
     ['9-10',  add, -2],
     ['11',    add, -1],
     ['12-13', mult, 1],
     ['14',    add,  1],
     ['15-16', add,  2],
     ['17-18', add,  3],
     ['19-25', mult, 2],
     ['26-30', mult, 2.5],
     ['31-35', mult, 3],
     ['36-39', mult, 4],
     ['40+',   mult, 8]];


function buildDamageTable(parent, chart) {
    var table = $('<table></table>');
    var hrow = $('<tr></tr>');
    $.each(chart, function() {
        var th = $('<th>' + this[0] + '</th>');
        hrow.append(th);
    });
    table.append(hrow);
    $(parent).append(table);
}

function jagsAdd(base, add) {
    if(Math.abs(base) > 10) {
        return base + (add/10) * base;
    }
    else {
        return base + add;
    }
}

function clearUpdateTable(id, chart) {
    $.each(chart, function(n) {
        $('#' + itemID(id, n)).text('');
    });
}

function makeUpdateTable(id, chart) {
    return function() {
        var base = parseInt($('#' + itemID(id, 's')).val());
        if (isNaN(base)) return clearUpdateTable(id, chart);
        var lastVal = 0;
        $.each(chart, function(n) {
            var op = this[1];
            var q = this[2];
            if(op != mult || q != 1) {
                var val;
                if(op == set) {
                    val = q
                } else if (op == mult) {
                    val = Math.max(base * q, lastVal);
                } else if (op == add) {
                    val = Math.max(jagsAdd(base, q), lastVal);
                }
                val = Math.round(val);
                $('#' + itemID(id, n)).text(val);
                lastVal = val;
            }
            else {
                lastVal = base;
            }
            
        });
    }
}

function makeAdder(root, chart) {
    return function() {
        var chartID = nextChartID();
        tr = $('<tr></tr>');
        $.each(chart, function(n) {
            var thisID = itemID(chartID, n);
            var td;
            if (this[1] != mult || this[2] != 1) {
                var thisID = itemID(chartID, n);
                td = $('<td><span id="'
                       + thisID
                       + '"></span></td>');
            }
            else {
                var thisID = itemID(chartID, 's');
                td = $('<td><form><input type="text" size="2" id="'
                       + thisID
                       + '"></form></td>');
                $('#' + thisID, td).typeWatch({
                    callback : makeUpdateTable(chartID, chart)
                });
            }
            tr.append(td);
        });
        $(root + ' table').append(tr);
        setupTextBoxHilighting(tr);
        return false;
    }
}

function makeDeletor(table) {
	return function() {
		$('tr:last', table).remove();
		return false;
	}
}

$(function () {
    buildDamageTable('#impact-damage-table', impactDamageChart);
    $('#add-impact-damage').click(makeAdder('#impact-damage-table',
                                            impactDamageChart)).click();
    $('#remove-impact-damage').click(makeDeletor('#impact-damage-table'));

    buildDamageTable('#penetration-damage-table', penetrationDamageChart);
    $('#add-penetration-damage').click(makeAdder('#penetration-damage-table',
                                                 penetrationDamageChart)).click();
    $('#remove-penetration-damage').click(makeDeletor('#penetration-damage-table'));

});

// Opposed Rolls

function setOpposedResult(result) {
	$('#opposed-roll-section #result').html(result);	
}

function getDivisor(min) {
	if (min <= 20) return 1;
	if (min <= 100) return 5;
	if (min <= 200) return 10;
	min = Math.ceil(min / 500);
	return 25 * min;
}

function recalculateOpposedScores() {
	var left = parseInt($('#left-score').val());
	var right = parseInt($('#right-score').val());
	if(isNaN(left) || isNaN(right) || left < 0 || right < 0) {
		setOpposedResults('&nbsp;');
	} else {
		var min = Math.min(left, right);
		var divisor = getDivisor(min);
		left = Math.round(left / divisor);
		right = Math.round(right / divisor);
		var diff = 10 + left - right;
		setOpposedResult('' + diff);	
	}
}

$(function() {
	$('#left-score, #right-score').typeWatch({
		callback : recalculateOpposedScores
	});
});



