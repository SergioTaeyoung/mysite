package com.douzone.mysite.action.board;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		if ("write".equals(actionName)) {
			return new WriteAction();
		} else if ("view".equals(actionName)) {
			return new ViewAction();
		} else if ("modify".equals(actionName)) {
			return new ModifyAction();
		} else if ("modifyAction".equals(actionName)) {
			return new ModifyAction2();
		} else if ("delete".equals(actionName)) {
			return new DeleteAction();
		} else if ("write_check".equals(actionName)) {
			return new WriteAction2();
		} else if ("search".equals(actionName)) {
			return new SearchAction();
		} else if ("paging".equals(actionName)) {
			return new PagingAction();
		}  else {
			return new ListAction();
		}
	}
}
