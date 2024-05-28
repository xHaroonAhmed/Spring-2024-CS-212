public class SortedWordList extends WordList {
  public SortedWordList(){
    super();
  }
  public void add(Word w){
    WordNode node=new WordNode(w);
    WordNode p1= head;// left pointer
    WordNode p2=head.next; // right pointer
    while(p2!=null && node.data.getWord().compareTo(p2.data.getWord())>0 ){
        p1=p1.next;
        p2=p2.next;
    }
    node.next=p2;
    p1.next=node;



  }

  public void print(){
    WordNode p=head.next;
    while(p!=null){
      System.out.println(p.data.getWord());
      p=p.next;
    }
  }
  
}
