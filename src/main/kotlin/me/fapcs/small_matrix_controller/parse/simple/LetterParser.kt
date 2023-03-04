package me.fapcs.small_matrix_controller.parse.simple

object LetterParser {

    private val letters = mapOf(
        'a' to " xxx " +
                "x   x" +
                "xxxxx" +
                "x   x" +
                "x   x",
        'b' to "xxxx " +
                "x   x" +
                "xxxx " +
                "x   x" +
                "xxxx ",
        'c' to " xxx " +
                "x   x" +
                "x    " +
                "x   x" +
                " xxx ",
        'd' to "xxxx " +
                "x   x" +
                "x   x" +
                "x   x" +
                "xxxx ",
        'e' to "xxxxx" +
                "x    " +
                "xxxx " +
                "x    " +
                "xxxxx",
        'f' to "xxxxx" +
                "x    " +
                "xxxx " +
                "x    " +
                "x    ",
        'g' to " xxx " +
                "x    " +
                "x  xx" +
                "x   x" +
                " xxx ",
        'h' to "x   x" +
                "x   x" +
                "xxxxx" +
                "x   x" +
                "x   x",
        'i' to " xxx " +
                "  x  " +
                "  x  " +
                "  x  " +
                " xxx ",
        'j' to "    x" +
                "    x" +
                "    x" +
                "x   x" +
                " xxx ",
        'k' to "x   x" +
                "x  x " +
                "xxx  " +
                "x  x " +
                "x   x",
        'l' to "x    " +
                "x    " +
                "x    " +
                "x    " +
                "xxxxx",
        'm' to "x   x" +
                "xx xx" +
                "x x x" +
                "x   x" +
                "x   x",
        'n' to "x   x" +
                "xx  x" +
                "x x x" +
                "x  xx" +
                "x   x",
        'o' to " xxx " +
                "x   x" +
                "x   x" +
                "x   x" +
                " xxx ",
        'p' to "xxxx " +
                "x   x" +
                "xxxx " +
                "x    " +
                "x    ",
        'q' to " xxx " +
                "x   x" +
                "x   x" +
                "x  x " +
                " xxxx",
        'r' to "xxxx " +
                "x   x" +
                "xxxx " +
                "x  x " +
                "x   x",
        's' to " xxxx" +
                "x    " +
                " xxx " +
                "    x" +
                "xxxx ",
        't' to "xxxxx" +
                "  x  " +
                "  x  " +
                "  x  " +
                "  x  ",
        'u' to "x   x" +
                "x   x" +
                "x   x" +
                "x   x" +
                " xxx ",
        'v' to "x   x" +
                "x   x" +
                "x   x" +
                " x x " +
                "  x  ",
        'w' to "x   x" +
                "x   x" +
                "x x x" +
                "xx xx" +
                "x   x",
        'x' to "x   x" +
                " x x " +
                "  x  " +
                " x x " +
                "x   x",
        'y' to "x   x" +
                " x x " +
                "  x  " +
                "  x  " +
                "  x  ",
        'z' to "xxxxx" +
                "   x " +
                "  x  " +
                " x   " +
                "xxxxx",
        'ö' to " x x " +
                "     " +
                " xxx " +
                "x   x" +
                " xxx ",
        'ü' to " x x " +
                "     " +
                "x   x" +
                "x   x" +
                " xxx ",
        'ä' to " x x " +
                " xxx " +
                "x   x" +
                "xxxxx" +
                "x   x",
        '0' to " xxx " +
                "x   x" +
                "x  xx" +
                "x x x" +
                " xxx ",
        '1' to "  x  " +
                " xx  " +
                "  x  " +
                "  x  " +
                "  x  ",
        '2' to " xxx " +
                "x   x" +
                "   x " +
                "  x  " +
                "xxxxx",
        '3' to "xxxxx" +
                "    x" +
                "  xx " +
                "    x" +
                "xxxxx",
        '4' to "x   x" +
                "x   x" +
                "xxxxx" +
                "    x" +
                "    x",
        '5' to "xxxxx" +
                "x    " +
                "xxxx " +
                "    x" +
                "xxxx ",
        '6' to " xxx " +
                "x    " +
                "xxxx " +
                "x   x" +
                " xxx ",
        '7' to "xxxxx" +
                "    x" +
                "   x " +
                "  x  " +
                " x   ",
        '8' to " xxx " +
                "x   x" +
                " xxx " +
                "x   x" +
                " xxx ",
        '9' to " xxx " +
                "x   x" +
                " xxxx" +
                "    x" +
                " xxx ",
        ' ' to "     " +
                "     " +
                "     " +
                "     " +
                "     ",
        '!' to "  x  " +
                "  x  " +
                "  x  " +
                "     " +
                "  x  ",
        '<' to "   x " +
                "  x  " +
                " x   " +
                "  x  " +
                "   x ",
        '>' to " x   " +
                "  x  " +
                "   x " +
                "  x  " +
                " x   ",
        '.' to "     " +
                "     " +
                "     " +
                "     " +
                "  x  ",
        ',' to "     " +
                "     " +
                "     " +
                "  x  " +
                " x   ",
        '-' to "     " +
                "     " +
                "xxxxx" +
                "     " +
                "     "
    )

    fun parse(char: Char) = (letters[char.lowercaseChar()] ?: letters[' ']!!)
        .chunked(5)
        .map { row ->
            row
                .toCharArray()
                .map { it == 'x' }
                .toTypedArray()
        }
        .toTypedArray()


}