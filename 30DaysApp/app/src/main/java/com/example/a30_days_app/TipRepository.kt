package com.example.a30_days_app

object TipRepository {
    fun getTips(): List<Tip> = listOf(
        Tip(
            day = 1,
            title = "Write Code Every Day",
            description = "Consistency beats talent. Even 20–30 minutes of coding daily builds strong habits and keeps your skills sharp. Use coding challenges like LeetCode or HackerRank to stay motivated.",
            imageRes = R.drawable.tip_day_01
        ),
        Tip(
            day = 2,
            title = "Learn Keyboard Shortcuts",
            description = "Mastering IDE shortcuts (like Ctrl+Shift+F for search or Alt+Enter for quick fixes in Android Studio) dramatically speeds up your workflow and makes you look like a pro.",
            imageRes = R.drawable.tip_day_02
        ),
        Tip(
            day = 3,
            title = "Read Other People's Code",
            description = "Browse open-source projects on GitHub. Reading real-world code exposes you to patterns, naming conventions, and problem-solving approaches you won't find in textbooks.",
            imageRes = R.drawable.tip_day_03
        ),
        Tip(
            day = 4,
            title = "Practice Debugging",
            description = "Don't fear bugs — embrace them. Learn to use breakpoints, logcat, and the debugger in your IDE. Every bug you fix teaches you something new about how code actually runs.",
            imageRes = R.drawable.tip_day_04
        ),
        Tip(
            day = 5,
            title = "Contribute to Open Source",
            description = "Find a project you use and fix a small bug or improve documentation. Contributing to open source builds real experience, expands your network, and strengthens your portfolio.",
            imageRes = R.drawable.tip_day_05
        ),
        Tip(
            day = 6,
            title = "Build Small Projects",
            description = "Don't wait until you're 'ready' for big projects. Build a todo app, a calculator, or a weather app. Finishing small projects gives you confidence and hands-on practice.",
            imageRes = R.drawable.tip_day_06
        ),
        Tip(
            day = 7,
            title = "Refactor Old Code",
            description = "Revisit code you wrote months ago. Refactoring teaches you how your thinking has evolved and introduces you to cleaner patterns, better naming, and improved structure.",
            imageRes = R.drawable.tip_day_07
        ),
        Tip(
            day = 8,
            title = "Write Clean Functions",
            description = "A good function does ONE thing and does it well. Keep functions short, name them clearly (e.g. calculateTotalPrice()), and avoid side effects. Clean code is easier to test and maintain.",
            imageRes = R.drawable.tip_day_08
        ),
        Tip(
            day = 9,
            title = "Learn Git",
            description = "Version control is non-negotiable in professional development. Learn git commit, branch, merge, and rebase. Use GitHub or GitLab to host your projects and track your progress.",
            imageRes = R.drawable.tip_day_09
        ),
        Tip(
            day = 10,
            title = "Understand APIs",
            description = "APIs are the backbone of modern apps. Learn what REST means, how HTTP methods work (GET, POST, PUT, DELETE), and practice calling a free public API like OpenWeather or PokéAPI.",
            imageRes = R.drawable.tip_day_10
        ),
        Tip(
            day = 11,
            title = "Write Unit Tests",
            description = "Tests save you from yourself. Write tests for your functions before or after writing them. Even basic unit tests catch bugs early and give you confidence when refactoring.",
            imageRes = R.drawable.tip_day_11
        ),
        Tip(
            day = 12,
            title = "Study Data Structures",
            description = "Knowing when to use a list vs a map vs a set can make your code 10x faster. Study arrays, linked lists, stacks, queues, hashmaps, and trees — they appear everywhere.",
            imageRes = R.drawable.tip_day_12
        ),
        Tip(
            day = 13,
            title = "Learn About Algorithms",
            description = "Sorting, searching, recursion, and dynamic programming are core skills. Practice on platforms like LeetCode or Codewars. Start easy and work your way up step by step.",
            imageRes = R.drawable.tip_day_13
        ),
        Tip(
            day = 14,
            title = "Understand the MVC/MVVM Pattern",
            description = "Architecture patterns like MVVM (Model-View-ViewModel) separate concerns and make apps maintainable. For Android, MVVM with ViewModel and LiveData (or StateFlow) is the modern standard.",
            imageRes = R.drawable.tip_day_14
        ),
        Tip(
            day = 15,
            title = "Document Your Code",
            description = "Write comments that explain WHY, not just WHAT. Use KDoc in Kotlin to document public functions and classes. Good documentation makes your code a gift to your future self.",
            imageRes = R.drawable.tip_day_15
        ),
        Tip(
            day = 16,
            title = "Learn SQL Basics",
            description = "Most apps store data. Understanding SQL queries — SELECT, INSERT, UPDATE, DELETE, JOIN — is essential whether you use SQLite, Room, or any backend database.",
            imageRes = R.drawable.tip_day_16
        ),
        Tip(
            day = 17,
            title = "Explore Kotlin Coroutines",
            description = "Coroutines make async code readable and safe in Android. Learn launch, async, suspend functions, and Dispatchers. They replace messy callbacks and make network calls clean.",
            imageRes = R.drawable.tip_day_17
        ),
        Tip(
            day = 18,
            title = "Practice Problem Solving Daily",
            description = "Solve at least one coding problem per day on platforms like LeetCode, HackerRank, or Codewars. Even 15 minutes of problem-solving sharpens your logic and interview skills.",
            imageRes = R.drawable.tip_day_18
        ),
        Tip(
            day = 19,
            title = "Learn About Clean Architecture",
            description = "Clean Architecture separates your app into layers: UI, Domain, and Data. This makes code testable, scalable, and easier to work on in teams. Study Uncle Bob's principles.",
            imageRes = R.drawable.tip_day_19
        ),
        Tip(
            day = 20,
            title = "Understand Memory Management",
            description = "Memory leaks crash apps. In Android, avoid holding Context references in ViewModels, use WeakReference when needed, and profile your app with Android Studio's Memory Profiler.",
            imageRes = R.drawable.tip_day_20
        ),
        Tip(
            day = 21,
            title = "Master Dependency Injection",
            description = "DI frameworks like Hilt (Android) or Koin decouple your components, making your code testable and modular. Learn constructor injection and understand the dependency graph.",
            imageRes = R.drawable.tip_day_21
        ),
        Tip(
            day = 22,
            title = "Study UI/UX Principles",
            description = "Good developers care about user experience. Study Google's Material Design guidelines, learn about spacing, typography, and color contrast, and always design with accessibility in mind.",
            imageRes = R.drawable.tip_day_22
        ),
        Tip(
            day = 23,
            title = "Learn Networking Basics",
            description = "Understand how HTTP, HTTPS, and TCP/IP work. In Android, use Retrofit for API calls. Learn about JSON parsing, error handling, and how to secure your network requests.",
            imageRes = R.drawable.tip_day_23
        ),
        Tip(
            day = 24,
            title = "Use a Linter / Code Formatter",
            description = "Tools like ktlint or Detekt automatically enforce code style. Consistent formatting makes code easier to read and reduces noise in code reviews. Set it up once, benefit forever.",
            imageRes = R.drawable.tip_day_24
        ),
        Tip(
            day = 25,
            title = "Learn About Security",
            description = "Never store passwords in plain text. Use HTTPS everywhere. In Android, use EncryptedSharedPreferences for sensitive data. Study OWASP Mobile Top 10 threats to know what to avoid.",
            imageRes = R.drawable.tip_day_25
        ),
        Tip(
            day = 26,
            title = "Attend Developer Communities",
            description = "Join local meetups, Discord servers, Reddit communities (r/androiddev), or Stack Overflow. Learning from peers, asking questions, and sharing knowledge accelerates your growth.",
            imageRes = R.drawable.tip_day_26
        ),
        Tip(
            day = 27,
            title = "Build a Portfolio",
            description = "Create a GitHub profile that showcases 3–5 quality projects. A strong portfolio speaks louder than a CV. Include a README for each project with screenshots and description.",
            imageRes = R.drawable.tip_day_27
        ),
        Tip(
            day = 28,
            title = "Understand Accessibility",
            description = "Make your apps usable for everyone. Add content descriptions to images, ensure sufficient color contrast, support font scaling, and test with TalkBack (Android's screen reader).",
            imageRes = R.drawable.tip_day_28
        ),
        Tip(
            day = 29,
            title = "Learn Continuous Integration",
            description = "CI tools like GitHub Actions automatically build and test your code when you push changes. Set up a basic pipeline that runs your unit tests — it's a professional habit that pays off.",
            imageRes = R.drawable.tip_day_29
        ),
        Tip(
            day = 30,
            title = "Never Stop Learning",
            description = "Technology changes fast. Read developer blogs, watch conference talks (Google I/O, KotlinConf), follow official changelogs, and dedicate time each week to learning something new. You've made it — keep going!",
            imageRes = R.drawable.tip_day_30
        )
    )
}