import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class BabySitterSpec : Spek({

    data class Schedule(val arrival: Int, val departure: Int, val bedtime: Int)

    given("A Babysitter") {

        val data = listOf(
                Schedule(5, 6, 8) to 10,
                Schedule(5, 7, 8) to 20,
                Schedule(8, 9, 8) to 6,
                Schedule(8, 10, 8) to 12,
                Schedule(1, 2, 8) to 20,
                Schedule(9, 11, 8) to 12,
                Schedule(12, 2, 8) to 40,
                Schedule(5, 1, 8) to 74
        )

        for ((schedule, payment) in data) {
            on("works from ${schedule.arrival} to ${schedule.departure} with a bedtime of ${schedule.bedtime}") {

                val result = calculatePayment(schedule.arrival, schedule.departure, schedule.bedtime)

                it("should get paid $payment") {
                    assertEquals(payment, result)
                }
            }
        }
    }

})
