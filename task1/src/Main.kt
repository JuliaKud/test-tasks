const val NAME = "alex"
const val ALPHABET_SIZE = 26

fun main() {
    val n = readln().toInt()
    val graph = Array(ALPHABET_SIZE) { HashSet<Int>() }

    var samePrefix = mutableListOf<String>().apply {
        repeat(n) {
            val name = readln()
            if (name[0] == NAME[0]) {
                add(name)
            } else {
                graph[NAME[0] - 'a'].add(name[0] - 'a')
            }
        }
    }
    /* this piece of code does basically the same as the next one but for optimization purposes
    we avoid creating a mutable list of all given names when it is enough to keep names
    that start with the same letter as NAME */

    for (i in 1..<NAME.length) {
        val biggerPrefix = mutableListOf<String>()
        for (name in samePrefix) {
            if (name.length == i) {
                println("Impossible")
                return
            }
            if (name[i] == NAME[i]) {
                biggerPrefix.add(name)
            } else {
                graph[NAME[i] - 'a'].add(name[i] - 'a')
            }
        }
        samePrefix = biggerPrefix
    }
    /* note: we suppose that NAME will not be present in a given list of names,
    otherwise the answer would be ambiguous */

    val alphabet = StringBuilder()
    val visited = Array(ALPHABET_SIZE) { 0 }
    var count = 0

    fun topSort(node: Int): Boolean {
        visited[node] = count
        for (elem in graph[node]) {
            if (visited[elem] == count) {
                return false
            }
            if (visited[elem] == 0) {
                topSort(elem).also { if (!it) return false }
            }
        }
        alphabet.append('a' + node)
        return true
    }

    for (i in 0..<ALPHABET_SIZE) {
        if (visited[i] == 0) {
            count += 1
            topSort(i).also {
                if (!it) {
                    println("Impossible")
                    return
                }
            }
        }
    }

    alphabet.reverse()
    println(alphabet)
}
