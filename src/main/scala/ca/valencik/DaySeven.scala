package ca.valencik

import ca.valencik.Utils.putStrLn

object DaySeven extends App {

  def maybeIndexOfOddBall[A](xs: List[A]): Option[Int] = {
    if (xs.length < 3)
      None
    else {
      val three = xs.take(3)
      if (three.distinct.length == 1) {
        val oddIndex = xs.indexWhere(_ != xs.head)
        if (oddIndex >= 0) Some(oddIndex) else None
      } else {
        val oddValue =
          three.groupBy(identity).mapValues(_.length).filter(_._2 == 1).head._1
        Some(xs.indexOf(oddValue))
      }
    }
  }

  case class TreeProper(name: String, weight: Int, children: List[TreeProper]) {
    def sumWeight(): Int = {
      def innerWeight(acc: Int, kids: List[TreeProper]): Int = {
        if (kids.length == 0)
          acc
        else
          innerWeight(acc + kids.map(_.weight).sum, kids.flatMap(_.children))
      }
      innerWeight(weight, children)
    }
    lazy val childWeights                 = children.map(_.sumWeight)
    lazy val numChildren                  = children.length
    lazy val hasChildrenBalanced: Boolean = childWeights.distinct.length == 1
    lazy val unbalancedChild: Option[TreeProper] = {
      maybeIndexOfOddBall(childWeights) match {
        case Some(i) => Some(children(i))
        case None    => None
      }
    }
    lazy val balancedChild = children.find(_.hasChildrenBalanced)
  }

  case class TreeNode(name: String, weight: String, children: List[String]) {
    def getWeight(): Int = weight.trim.stripPrefix("(").stripSuffix(")").toInt
  }

  def splitTree(treeString: String): List[String] = {
    treeString.split(",?\\s+").toList
  }

  def genTreeNodes(lines: Iterator[String]): List[TreeNode] = {
    lines
      .filter(_.nonEmpty)
      .map(splitTree)
      .flatMap {
        _ match {
          case node :: weight :: Nil => Some(TreeNode(node, weight, Nil))
          case node :: weight :: arrow :: branches =>
            Some(TreeNode(node, weight, branches))
          case _ => None
        }
      }
      .toList
  }

  def recurseToRoot(start: TreeNode, nodes: List[TreeNode]): TreeNode = {
    def inner(currNode: TreeNode): TreeNode = {
      val index = nodes.indexWhere(_.children.contains(currNode.name))
      if (index >= 0)
        inner(nodes(index))
      else
        currNode
    }
    inner(start)
  }

  def constructTreeProperFromTreeNodes(tns: List[TreeNode]): TreeProper = {
    val root = recurseToRoot(tns.head, tns)
    def innerConstruct(tn: TreeNode): TreeProper = {
      if (tn.children.isEmpty)
        TreeProper(tn.name, tn.getWeight, List())
      else
        TreeProper(
          tn.name,
          tn.getWeight,
          tns.filter(tn.children contains _.name).map(innerConstruct)
        )
    }
    innerConstruct(root)
  }

  def balanceTree(root: TreeProper): Option[Int] = {
    def inner(grandParent: TreeProper, parent: TreeProper): Option[Int] = {
      parent.unbalancedChild match {
        case Some(c) => inner(parent, c)
        case None =>
          Some(
            grandParent.balancedChild.get.sumWeight - parent.childWeights.sum)
      }
    }
    inner(root, root)
  }

  def process(input: String): Iterator[String] = {
    scala.io.Source.fromFile(input).getLines()
  }

  def partOne(lines: Iterator[String]): String = {
    val treeNodes    = genTreeNodes(lines)
    val startingNode = treeNodes.head
    val root         = recurseToRoot(startingNode, treeNodes)
    root.name
  }

  def partTwo(lines: Iterator[String]): Int = {
    val treeNodes = genTreeNodes(lines)
    val root      = constructTreeProperFromTreeNodes(treeNodes)
    balanceTree(root).get
  }

  putStrLn("partOne: " + partOne(process(args(0))))
  putStrLn("partTwo: " + partTwo(process(args(0))))
}
