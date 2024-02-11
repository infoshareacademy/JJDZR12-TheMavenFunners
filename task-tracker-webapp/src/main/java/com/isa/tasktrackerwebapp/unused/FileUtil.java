package com.isa.tasktrackerwebapp.unused;

import java.io.File;
import java.nio.file.Path;

public class FileUtil {
    private static final Path ROOT_PATH = Path.of("src", "main", "resources", "database");
    private static final Path USERS_PATH = Path.of(ROOT_PATH.toString(), "users.json");
    private static final Path TASKS_PATH = Path.of(ROOT_PATH.toString(), "tasks.json");

    private static final File USERS_FILE = new File(USERS_PATH.toString());
    private static final File TASKS_FILE = new File(TASKS_PATH.toString());

    private FileUtil() {
    }

    public static File getUsersFile() {
        return USERS_FILE;
    }

    public static File getTasksFile() {
        return TASKS_FILE;
    }
}
