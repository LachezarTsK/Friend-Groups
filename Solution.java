package friendGroups.gitHub;

public class Solution {

  /*
  By the problem design on binarysearch.com, we have to work
  around the given method 'public int solve(int[][] friends)'
  so that the code can be run on the website. Even though the name 'solve'
  does not make a lot of sense, it is left as it is, so that the code can
  be run directly on the website, without any modifications.
  */
  public int solve(int[][] friends) {
    return findTotalFriendGroups(friends);
  }

  public int findTotalFriendGroups(int[][] friends) {

    UnionFind unionFind = new UnionFind(friends.length);
    int totalFriendGroups = friends.length;

    for (int r = 0; r < friends.length; r++) {
      for (int c = 0; c < friends[r].length; c++) {

        int personOne = unionFind.findParent(r);
        int personTwo = unionFind.findParent(friends[r][c]);

        if (personOne != personTwo) {
          unionFind.union(personOne, personTwo);
          totalFriendGroups--;
        }
      }
    }

    return totalFriendGroups;
  }
}

class UnionFind {

  int numberOfPeople;
  Subset[] subsets;

  public UnionFind(int numberOfPeople) {

    this.numberOfPeople = numberOfPeople;
    this.subsets = new Subset[numberOfPeople];

    for (int i = 0; i < numberOfPeople; i++) {
      subsets[i] = new Subset();
      subsets[i].parent = i;
    }
  }

  public int findParent(int person) {

    if (subsets[person].parent != person) {
      subsets[person].parent = findParent(subsets[person].parent);
    }
    return subsets[person].parent;
  }

  public void union(int personOne, int personTwo) {

    if (subsets[personOne].rank > subsets[personTwo].rank) {
      subsets[personTwo].parent = personOne;
    } else if (subsets[personOne].rank < subsets[personTwo].rank) {
      subsets[personOne].parent = personTwo;
    } else {
      subsets[personTwo].parent = personOne;
      subsets[personOne].rank++;
    }
  }
}

class Subset {

  int parent;
  int rank;
}
