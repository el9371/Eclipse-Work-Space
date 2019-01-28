package pocket;


public class DialogueText {
	private int type;
	private String text;
	private DialogueText next;
	public DialogueText(int _type, String _text) {
		this.type = _type;
		this.text = _text;
		this.next = null;
	} 
	public DialogueText(String _text) {
		this.text = _text;
		this.type = 0;
		this.next = null;
	}
	public DialogueText(String _text, DialogueText _next) {
		this.text = _text;
		this.type = 0;
		this.next = _next;
	}
	public DialogueText(int _type, String _text, DialogueText _next) {
		this.text = _text;
		this.type = _type;
		this.next = _next;
	}
	public void setNext(DialogueText d) {
		this.next = d;
	}
	public void setNextDialogue(DialogueText d) {
		DialogueText tmp = this;
		while (tmp.getNext() != null) tmp = tmp.getNext();
		tmp.setNext(d);
	}
	public int getType() {
		return type;
	}
	public String getText() {
		return text;
	}
	public DialogueText getNext() {
		return next;
	}
	public void printDialogue() {
		DialogueText tmp = this.getNext();
		while (tmp != null) {
			System.out.println(tmp.getText());
			tmp = tmp.getNext();
		}
	}
}
