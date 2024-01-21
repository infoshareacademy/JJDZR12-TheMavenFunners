alter table task_tracker.user
    add constraint unique_email
        unique (email);

alter table task_tracker.user
    add constraint unique_login
        unique (login);

alter table task_tracker.task
    AUTO_INCREMENT=1;

alter table task_tracker.task
    add constraint task_data_check
        check (`task_end` >= `task_start`);