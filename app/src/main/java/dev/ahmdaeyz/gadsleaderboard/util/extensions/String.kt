package dev.ahmdaeyz.gadsleaderboard.util.extensions


fun String.isEmail(): Boolean {
    return "^(.+)@(.+)\$".toRegex().matches(this)
}


fun String.isAGithubUrl(): Boolean {
    return "https://github.com/(\\w)\\w+/(\\w)\\w+".toRegex().matches(this)
}