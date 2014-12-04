
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class MemberModel extends AbstractTableModel {


	Vector column = new Vector();
	Vector list = new Vector(); 

	public MemberModel() {
		column.add("제목");
		column.add("저자");
		column.add("출판사");
		column.add("ISBN");
		column.add("대여가능여부");
		column.add("대여한 학생");
	}
	
	
	public String getColumnName(int index) {
		return String.valueOf(column.get(index));
	}

	public void setList(Vector list) {
		this.list = list;
	}

	@Override
	public int getColumnCount() {		
		return column.size(); 
	}

	@Override
	public int getRowCount() {
		
		return list.size(); 
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		Vector vec = (Vector) list.get(row);
		return vec.get(col);
	}
	
	public void resetlist()
	{
		this.list.clear();
	}

}