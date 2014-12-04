
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class MemberModel extends AbstractTableModel {


	Vector column = new Vector();
	Vector list = new Vector(); 

	public MemberModel() {
		column.add("브랜드명");
		column.add("제품이름");
		column.add("제품유형");
		column.add("제품기능");
		column.add("피부타입");
		column.add("사용결과");
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