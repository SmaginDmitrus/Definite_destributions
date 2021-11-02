import kotlin.random.Random
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import kotlin.math.*

fun ex1 (x:Double):Double =  exp(sin(PI *(x.pow(2)))) //функция для задачи с экспонентой
fun ex2 (x:Double):Double = 1/sqrt(x) // функция для задачи с корнем

fun main() {
    // взаимодействие с пользователем. Спрашиваем, какое задание он хочет посмотреть
    println("To run ex1 enter 1, to run ex2 enter 2")
    val maxValue:Double = 1.0 // значение, выше которого обе функции не могут быть
    var input = readLine()
    var a = 0.0
    var b = 0.0
    var n = 0
    val gen = Random(0)
    val intervals = arrayListOf<Double>()// массив с интервалами от 0.01 до 0.6
    var values = arrayListOf<Int>() // массив с количеством точек, которые попали в интервал
    val xs = arrayListOf<Double>() // для работы с методами
    val ys = arrayListOf<Double>() // для работы с методами

    var sum:Double = 0.01
    //заполняем массив с интервалами
    while (sum <=maxValue) {
        intervals.add(sum)
        sum+=0.01
    }

    for(i in 1..(intervals.size)) values.add(0)
    sum = 0.0

    if (input == "1"){
        //метод отбрасывания неподходящих точек
        while(n<15000) {
            a = gen.nextDouble()
            b = 3*gen.nextDouble()
            if(b<= ex1(a)){
                xs.add(a)
                ys.add(b)
                n++
            }
        }
    }else{
        //метод обращения
        while(n<15000){
            a = ((2*(gen.nextDouble())).pow(2))/4
            if (a != 0.0) {
                xs.add(a)
                b = ex2(a)
                ys.add(b)
                n++
            }
        }
    }

        for (i in xs)
        {
            sum += i
        }

    var sre = sum/n //считаем среднее значение
    println ("Average value = $sre")

    for (i in 0 .. (intervals.size - 1))//смотрим сколько точек попадает в интервал, кидаем результат в массив values
    {
        for (j in xs)
        {
         if (j>(intervals[i]- 0.01) && j<intervals[i]) values[i] += 1
        }
    }



        val data = mapOf<String, Any>("x" to intervals, "y" to values)//строим график: интервал -> кол-во точек в интервале
        val fig = letsPlot(data) + geomPoint(
            color = "dark-green",
            size = 1.0
        ) { x = "x"; y = "y" }
        ggsave(fig, "sqrt.png")

}


