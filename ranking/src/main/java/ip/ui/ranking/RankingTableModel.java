package ip.ui.ranking;

import ip.entities.Driver;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Wojciech Szałapski
 */
public class RankingTableModel extends AbstractTableModel {

    private final List<Driver> drivers;

    private final boolean isEco;

    public RankingTableModel(List<Driver> drivers, boolean isEco) {
        this.drivers = drivers;
        this.isEco = isEco;

        if (isEco) {
            Collections.sort(this.drivers, (d1, d2) -> (d2.getEcoPoints() - d1.getEcoPoints()));
        } else {
            Collections.sort(this.drivers, (d1, d2) -> (d2.getSafePoints() - d1.getSafePoints()));
        }
    }

    @Override
    public int getRowCount() {
        return drivers.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Driver driver = drivers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return driver.getNickname();
            case 1:
                if (isEco) {
                    return driver.getEcoPoints();
                } else {
                    return driver.getSafePoints();
                }
        }
        throw new IllegalArgumentException("Wrong column index: " + columnIndex);
    }
}
