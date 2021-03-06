package com.finalproject.controller.command.user;

import com.finalproject.controller.command.Command;
import com.finalproject.model.dao.service.ActionReportService;
import com.finalproject.model.entity.ActionReport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class UserActionReport implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        List<ActionReport> actionReportList = ActionReportService.userList(userId);
        actionReportList = actionReportList.stream()
                .filter(p -> p.getAction().equals(ActionReport.Action.EDIT))
                .collect(Collectors.toList());

        //TODO mb set to request, not to session
        request.getSession().setAttribute("userActionReportList", actionReportList);
        return "/WEB-INF/user/user-action-report.jsp";
    }
}


