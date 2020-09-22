package a.monitor.scan.baseprogram.baselearn

/**
@author:jacky
@Date:2020/9/21
 **/
class ExpandFunction {


    fun letFunction() {
        //在函数内可以通过it指代该对象
        //返回值为函数块的最后一行或指定return表达式
        /*
        val text ="HelloWorld"
        println(text)
        val result = 1000
        println(result)
        */

        val result = "Hello World".let {
            println(it.length)
            1000
        }
        println(result)
    }

    fun withFunction() {
   //调用同一个类的多个方法时，可以省去类名重复,可以使用with函数
        var result = with(Person("Jacky",19)) {
            println(name+age)
            1000
        }
    }

    fun runFunction() {
        //结合let和with，适用于let和with的任意场景
        var person = Person("CurvedBowZhang", 100)
        var result = person.run {
            println("$name + $age")
           // println("姓名：$name")
            1000
        }
        println(result)
    }

    fun applyFunction() {
        //run函数是以闭包形式返回最后一行代码的值，而apply函数的返回的是传入对象的本身
        val person = Person("Jacky",19)
        var result = person.apply {
            name = "tallow"
            age = 18
        }

        //另外apply行数还可以用于层级判空
    }

    fun alsoFunction() {

        //also函数和let函数很像let是以闭包的形式返回，
        // 返回函数体内最后一行的值，如果最后一行为空就返回一个Unit类型的默认值。而also函数返回的则是传入对象的本身
        val result = "Jacky".also {
            println(it.length)
        }
        println(result)
    }
}

class Person(var name: String, var age: Int)