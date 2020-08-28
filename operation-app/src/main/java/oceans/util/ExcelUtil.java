package oceans.util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    public static void export(String[] titles, String[] keyList, ServletOutputStream out, List<Map<String,Object>> mapList) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }
            for (int i = 0; i < mapList.size(); i++) {
                row = hssfSheet.createRow(i+1);
                Map<String,Object> map = mapList.get(i);
                for(int j=0;j<keyList.length;j++) {
                    if(map.get(keyList[j])==null){
                        row.createCell(j).setCellValue("");
                    }else{
                        row.createCell(j).setCellValue(map.get(keyList[j]).toString());
                    }
                }
            }
            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }
    }
}
