/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.table.DefaultTableModel;
import java.util.*;
/**
 *
 * @author bdevore
 */
public class ReadOnlyTableModel extends DefaultTableModel{
    public ReadOnlyTableModel(Vector data, Vector columnNames){
        super(data,columnNames);
    }
    
    public ReadOnlyTableModel(Object[][] data, Object[] columnNames){
        super(data,columnNames);
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        if(column==0 && row<23)
            return true;
        return false;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
    if (columnIndex == 0)
        return Boolean.class;
    return String.class;
}
}

