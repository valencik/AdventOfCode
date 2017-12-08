package ca.valencik

import ca.valencik.Utils.putStrLn

object DaySeven extends App {

  case class TreeNode(name: String, weight: String, children: List[String])

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

  def process(input: String): Iterator[String] = {
    scala.io.Source.fromFile(input).getLines()
  }

  def partOne(lines: Iterator[String]): String = {
    val treeNodes    = genTreeNodes(lines)
    val startingNode = treeNodes.head
    val root         = recurseToRoot(startingNode, treeNodes)
    root.name
  }

  putStrLn("partOne: " + partOne(process(args(0))))
}
