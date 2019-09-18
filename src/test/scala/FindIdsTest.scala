import org.scalatest._

class FindIdsTest extends FlatSpec with Matchers {

  "Find ids" should "work with zero late insertion" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2011-09-09"),
      (3, "2011-11-09"),
      (4, "2011-12-09"),
      (5, "2015-12-09")
    )) shouldBe Seq.empty
  }

  it should "work with empty table" in {
    FindIds.findIds(Seq.empty) shouldBe Seq.empty
  }

   it should "work with one late insertion" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2011-09-09"),
      (3, "2011-11-09"),
      (4, "2010-12-09"),
      (5, "2015-12-09")
    )) shouldBe Seq(4)
  }

  it should "work with two late insertions" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2011-09-09"),
      (3, "2011-11-09"),
      (4, "2010-12-09"),
      (5, "2009-12-09"),
      (6, "2015-12-09")
    )) shouldBe Seq(4,5)
  }

  it should "work with two late insertions number two" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2011-09-09"),
      (3, "2011-11-09"),
      (4, "2008-12-09"),
      (5, "2009-12-09"),
      (6, "2015-12-09")
    )) shouldBe Seq(4,5)
  }

  it should "work with three late insertions when last item is late inserted" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2011-09-09"),
      (3, "2011-11-09"),
      (4, "2010-12-09"),
      (5, "2009-12-09"),
      (6, "2015-12-09"),
      (7, "2008-11-09")
    )) shouldBe Seq(4,5,7)
  }

  it should "work with three late insertions when two last items are late inserted" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2011-09-09"),
      (3, "2011-11-09"),
      (4, "2010-12-09"),
      (5, "2009-12-09"),
      (6, "2015-12-09"),
      (7, "2008-11-09"),
      (8, "1998-11-09")
    )) shouldBe Seq(4,5,7,8)
  }

  it should "work with three late insertions" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2011-09-09"),
      (3, "2011-11-09"),
      (4, "2010-12-09"),
      (5, "2009-12-09"),
      (6, "2015-12-09"),
      (7, "2008-11-09"),
      (8, "2016-11-10"),
      (9, "2018-12-10")
    )) shouldBe Seq(4,5,7)
  }

  it should "work with three late insertions number 2" in {
    FindIds.findIds(Seq((1, "2010-09-09"),
      (2, "2010-10-09"),
      (3, "2011-11-09"),
      (4, "2010-12-09"),
      (5, "2009-12-09"),
      (6, "2015-12-09"),
      (7, "2015-12-10"),
      (8, "2014-11-10"),
      (9, "2018-12-10")
    )) shouldBe Seq(4,5,8)
  }
}
