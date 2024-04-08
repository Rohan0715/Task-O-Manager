package com.example.todoletsdo.ui.theme

class Task(
    val id: Int,
    val title: String,
    val body: String? = null,
    val startTime: String,
    val date: String
)

val taskList = listOf(
    Task(
        id = 1,
        title = "Code Review",
        body = "Review the pull requests and provide feedback",
        startTime = "10:00",
        date = "2024-04-01"
    ),
    Task(
        id = 2,
        title = "Gaming Time",
        body = "Valo time Baby!!",
        startTime = "12:00",
        date = "2024-04-02"
    ),
    Task(
        id = 3,
        title = "Bug Fixing",
        body = "Fix the reported bugs in the code",
        startTime = "2:00",
        date = "2024-04-03"
    ),
    Task(
        id = 4,
        title = "Sleep Time",
        body = "Rest and rejuvenate for the next day",
        startTime = "5:00",
        date = "2024-04-04"
    ),
    Task(
        id = 5,
        title = "New Feature Development",
        body = "Start working on the new feature as per the project plan",
        startTime = "2:00",
        date = "2024-04-05"
    ),
    Task(
        id = 6,
        title = "Online Gaming Tournament",
        body = "Participate in an online gaming tournament",
        startTime = "10:00",
        date = "2024-04-06"
    ),
    Task(
        id = 7,
        title = "Learning Time",
        body = "Learn a new coding language or gaming strategy",
        startTime = "12:00",
        date = "2024-04-07"
    ),
    Task(
        id = 8,
        title = "Code Optimization",
        body = "Optimize the existing code for better performance",
        startTime = "2:00",
        date = "2024-04-08"
    )
)
