
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class MemberModel extends AbstractTableModel {


	Vector column = new Vector();
	Vector list = new Vector(); 

	public MemberModel() {
		column.add("����");
		column.add("����");
		column.add("���ǻ�");
		column.add("ISBN");
		column.add("�뿩���ɿ���");
		column.add("�뿩�� �л�");
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