package util;

import model.Porder;
import service.impl.PorderServiceImpl;

import javax.swing.*;
import java.util.List;

public class PorderUi {

    public static void displayAllPorders(JTextArea allPorder, JLabel sum, PorderServiceImpl psi) {
        List<Porder> list = psi.findAllPorder();

        StringBuilder show = new StringBuilder();
        int count = 0;
        int dailySum = 0;
        int seasonSum = 0;
        int annualSum = 0;

        for (Porder p : list) {
            show.append("ID:").append(p.getId())
                .append("\t姓名:").append(p.getName())
                .append("\t\t單日票:").append(p.getDaily())
                .append("\t季票:").append(p.getSeason())
                .append("\t年票:").append(p.getAnnual()).append("\n");

            count++;
            dailySum += p.getDaily();
            seasonSum += p.getSeason();
            annualSum += p.getAnnual();
        }

        allPorder.setText(show.toString());

        int dailyTotal = dailySum * 299;
        int seasonTotal = seasonSum * 1899;
        int annualTotal = annualSum * 6999;
        int finalAmount = dailyTotal + seasonTotal + annualTotal;

        sum.setText("總訂單筆數:" + count +
                "\t單日票總數:" + dailySum +
                "\t季票總數:" + seasonSum +
                "\t年票總數:" + annualSum +
                "\t銷售總額:" + finalAmount + "元");
    }
}