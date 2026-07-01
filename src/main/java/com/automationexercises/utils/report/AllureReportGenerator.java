package com.automationexercises.utils.report;
import com.automationexercises.utils.OSUtils;
import com.automationexercises.utils.TerminalUtils;
import com.automationexercises.utils.TimeManager;
import com.automationexercises.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static com.automationexercises.utils.dataReader.PropertyReader.getProperty;
import static com.automationexercises.utils.report.AllureConstants.HISTORY_FOLDER;
import static com.automationexercises.utils.report.AllureConstants.RESULTS_HISTORY_FOLDER;

public class AllureReportGenerator {
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
    }

    public static String renameReport() {
        String newFileName = AllureConstants.REPORT_PREFIX + TimeManager.getTimestamp() + AllureConstants.REPORT_EXTENSION;
        com.automationexercises.FileUtils.renameFile(AllureConstants.REPORT_PATH.resolve(AllureConstants.INDEX_HTML).toString(), newFileName);
        return newFileName;
    }

    public static void openReport(String reportFileName) {
        if (!getProperty("executionType").toLowerCase().contains("local")) return;

        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSUtils.getCurrentOS()) {
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe", "/c", "start", reportPath.toString());
            case MAC, LINUX -> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.warn("Opening Allure Report is not supported on this OS.");
        }
    }

    public static void copyHistory() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            LogsManager.error("Error copying history files", e.getMessage());
        }
    }

}
