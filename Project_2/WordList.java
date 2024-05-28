public abstract class WordList {
  public WordNode head;
  public WordNode tail;
  public int length;
public WordList(){
  WordNode dn=new WordNode(null);
  head=dn;
  tail=dn;
  length=0;
}

public void append(Word w){
  WordNode node = new WordNode(w);
  tail.next=node;
  tail=node;
  length++;
}

  
}
