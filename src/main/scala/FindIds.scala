import java.text.SimpleDateFormat

object FindIds {
  private val datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd")

  private def fromDateString(str: String): Long = {
    datetimeFormatter.parse(str).getTime
  }

  def findIds(table: Seq[(Int, String)]): Seq[Int] = {
    var maxTimestamp = 0L

    val rawIds = for {
      row <- table
    } yield {
      val currentTimestamp = fromDateString(row._2)
      if (currentTimestamp > maxTimestamp) {
        maxTimestamp = currentTimestamp
        Option.empty
      } else {
        Some(row._1)
      }
    }

    rawIds.filter(_.isDefined).map(_.get)
  }

}
