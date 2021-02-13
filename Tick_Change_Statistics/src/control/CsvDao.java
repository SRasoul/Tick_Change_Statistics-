package control;

import java.util.List;

import model.DataRow;

public interface CsvDao {
List<DataRow> getDataList();
void scanAllstOcks(List<DataRow> dataRows);
}
