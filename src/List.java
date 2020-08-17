public class List {
    Integer data;
    List next;

    List(Integer data, List next) {
        this.data = data;
        this.next = next;
    }

    public List getLastList() {
        List p = this;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public void setNext(List list) {
        this.next = list;
    }
}
