import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

data class Bagbun(
    val problems: HashSet<Problem>
)

data class Problem(
    val hash: String,
    val data: HashSet<String>
)

fun main() {
    val filepath1 = readlnOrNull()
    val filepath2 = readlnOrNull()
    val outpath1 = readlnOrNull()
    val outpath2 = readlnOrNull()
    val outpathboth = readlnOrNull()

    if(filepath1 == null || filepath2 == null || outpath1 == null || outpath2 == null || outpathboth == null) {
        throw IllegalArgumentException("Incorrect input.")
    }

    val file1 = File(filepath1)
    val file2 = File(filepath2)

    if (!file1.exists() || !file2.exists()) {
        println("Please provide existing file paths for input files.")
        return
    }

    var gson = Gson()
    val bagbun1 = gson.fromJson(file1.readText(), Bagbun::class.java)
    val bagbun2 = gson.fromJson(file2.readText(), Bagbun::class.java)

    val problems1 = bagbun1.problems
    val problems2 = bagbun2.problems

    val uniqueProblems1 = problems1.subtract(problems2)
    val uniqueProblems2 = problems2.subtract(problems1)
    val commonProblems = problems1.intersect(problems2)

    gson = GsonBuilder().setPrettyPrinting().create()

    val res1 = gson.toJson(Bagbun(uniqueProblems1.toHashSet()))
    val res2 = gson.toJson(Bagbun(uniqueProblems2.toHashSet()))
    val resCommon = gson.toJson(Bagbun(commonProblems.toHashSet()))

    File(outpath1).writeText(res1)
    File(outpath2).writeText(res2)
    File(outpathboth).writeText(resCommon)
}
