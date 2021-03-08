fun main(args: Array<String>) {

/*    1.      Создайте в программе для авиакомпании еще пару классов, описывающих различные модели самолетов.
Сделайте один из них грузовым, для этого создайте соответствующий интерфейс со свойством грузоподъёмность.

    2.     В классе main используйте какую либо коллекцию для хранения информации об имеющихся самолетах у авиакомпании.
    Добавьте в эту коллекцию несколько самолетов используя имеющиеся классы моделей самолетов.

    3.      Напишите интерфейс для пользователя, где пользователь может в консоли запрашивать информацию о самолетах авиакомпании.
    Интерфейс должен отображать список команд, а пользователь в свою очередь выбирает номер команды.*/

    var fleet = ArrayList<Aircraft>()
    fleet.add(Boeing737(189, 10001, 5660, 16200))
    fleet.add(Boeing737(189, 10002, 5660, 16200))
    fleet.add(Boeing747(550, 10003, 14205, 241140))
    fleet.add(AirbusBeluga(53, 24390, 10004, 55000))
    fleet.add(AirbusBeluga(53, 24390, 10005, 55000))
    fleet.add(AirbusBeluga(53, 24390, 10006, 55000))

    println("Информация о самолетах авиакомпании")
    println("Нажмите - '1', чтобы узнать информацию по всем судам")
    println("Нажмите - '2', чтобы узнать информацию по пассажирским судам")
    println("Нажмите - '3', чтобы узнать информацию по грузовым судам")
    println("Нажмите - '4', чтобы узнать общее количество судов в компании")
    println("Нажмите - '0', чтобы выйти")

    do {
        println("Введите команду:")
        val command = readLine()
        when (command) {

            "1" -> {
                println("Все суда авиакомпании:")
                for (aircraft in fleet) {
                    aircraft.getInfo()
                }
            }

            "2" -> {
                println("Пассажирские суда авиакомпании:")
                for (aircraft in fleet) {
                    if (aircraft is Passenger) aircraft.getInfo()
                }
            }

            "3" -> {
                println("Грузовые суда авиакомпании:")
                for (aircraft in fleet) {
                    if (aircraft is Freighter) aircraft.getInfo()
                }
            }

            "4" -> println("Общее количество судов в компании: ${fleet.size}")

            "0" -> println("До новых встреч!")

            else -> println("Неподдерживаемая команда. Введите - '0', '1', '2', '3' или '4'")

        }

    } while (command != "0")

}

interface Freighter {
    var tonnage: Int
}

interface Passenger {
    var passengerCapacity: Int
}

class AirbusBeluga(override var tonnage: Int, number: Int, range: Int, tankCapacity: Int) : Aircraft(
    number,
    range,
    tankCapacity
), Freighter {

    override fun getInfo() {
        println("AirbusBeluga - $info Tonnage: $tonnage")
    }
}

class Boeing737(override var passengerCapacity: Int, number: Int, range: Int, tankCapacity: Int) : Aircraft(
    number,
    range,
    tankCapacity
), Passenger {

    override fun getInfo() {
        println("Boeing737 - $info PassengerCapacity: $passengerCapacity")
    }
}

class Boeing747(override var passengerCapacity: Int, number: Int, range: Int, tankCapacity: Int) : Aircraft(
    number,
    range,
    tankCapacity
), Passenger {

    override fun getInfo() {
        println("Boeing747 - $info PassengerCapacity: $passengerCapacity")
    }
}

abstract class Aircraft(number: Int, range: Int, tankCapacity: Int) {

    protected var number: Int = 0

    init {
        if (number > 0)
            this.number = number
    }

    protected var range: Int = 100

    init {
        if ((range > 100) and (range < 20000))
            this.range = range
    }

    protected var tankCapacity: Int = 10

    init {
        if ((tankCapacity > 5000) and (tankCapacity < 300000))
            this.tankCapacity = tankCapacity
    }

    protected val fuelConsumption: Double
        get() = tankCapacity.toDouble() / range * 100

    protected val info: String
        get() = "Number: $number Range: $range TankCapacity: $tankCapacity FuelConsumption: ${
            String.format(
                "%.2f",
                fuelConsumption
            )
        }"

    open fun getInfo() {
        println(info)
    }

}