package com.jagsrpg.jagsrolls.actions.game;

import java.util.List;

import com.jagsrpg.jagsrolls.model.Game;

public class AddAllInit extends AbstractModifier {

	private static final long serialVersionUID = -7593927364567513594L;

	private List<String> names;
	private List<Integer> reas;
	private List<Integer> inits;

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<Integer> getReas() {
		return reas;
	}

	public void setReas(List<Integer> reas) {
		this.reas = reas;
	}

	public List<Integer> getInits() {
		return inits;
	}

	public void setInits(List<Integer> inits) {
		this.inits = inits;
	}

	@Override
	public String perform(Game content, long version) {
		int size = names.size();
		for (int i = 0; i < size; ++i) {
			String name = names.get(i);
			int init = inits.get(i);
			int rea = reas.get(i);
			if (name.length() > 0 && name.length() <= 32) {
				content.addInitRoll(name, init, rea);
			}
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		super.validate();
		boolean good = names.size() == reas.size()
				&& names.size() == inits.size();
		if (!good) {
			addActionError("Bad list length");
			return;
		}
	}

}
