//package oceans.util;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.text.DecimalFormat;
//import java.util.*;
//
///**
// * 读取Excel
// *
// */
//public class ReadExcelUtils {
//    //随机生成2到5位的昵称
//    private static Random random = null;
//    private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
//    private Workbook wb;
//    private Sheet sheet;
//    private Row row;
//
//    public ReadExcelUtils(String filepath) {
//        if (filepath == null) {
//            return;
//        }
//        String ext = filepath.substring(filepath.lastIndexOf("."));
//        try {
//            InputStream is = new FileInputStream(filepath);
//            if (".xls".equals(ext)) {
//                wb = new HSSFWorkbook(is);
//            } else if (".xlsx".equals(ext)) {
//                wb = new XSSFWorkbook(is);
//            } else {
//                wb = null;
//            }
//        } catch (FileNotFoundException e) {
//            logger.error("FileNotFoundException", e);
//        } catch (IOException e) {
//            logger.error("IOException", e);
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            String filepath = "E:/opt/upload/file/activity/2018-09-19/test.xlsx";
//            ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
//            // 对读取Excel表格内容测试
//            List<Map<Integer, Object>> list = excelReader.readExcelContent();
//            System.out.println("获得Excel表格的内容:");
//            System.out.println(list.get(1).get(0));
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i));
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("未找到指定路径的文件!");
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Random getRandomInstance() {
//        if (random == null) {
//            random = new Random(new Date().getTime());
//        }
//        return random;
//    }
//
//    public static String getChinese() {
//        String str = null;
//        int highPos, lowPos;
//        Random random = getRandomInstance();
//        highPos = (176 + Math.abs(random.nextInt(39)));
//        lowPos = 161 + Math.abs(random.nextInt(93));
//        byte[] b = new byte[2];
//        b[0] = (new Integer(highPos)).byteValue();
//        b[1] = (new Integer(lowPos)).byteValue();
//        try {
//            str = new String(b, "GB2312");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return str;
//    }
//
//    public static String getRandomLengthChiness(int start, int end) {
//        String str = "";
//        int length = new Random().nextInt(end + 1);
//        if (length < start) {
//            str = getRandomLengthChiness(start, end);
//        } else {
//            for (int i = 0; i < length; i++) {
//                str = str + getChinese();
//            }
//        }
//        return str;
//    }
//
//    /**
//     * 读取Excel数据内容
//     *
//     * @return Map 包含单元格数据内容的Map对象
//     * @author pangyingsheng
//     */
//    public List<Map<Integer, Object>> readExcelContent() throws Exception {
//        if (wb == null) {
//            throw new Exception("Workbook对象为空！");
//        }
//        List<Map<Integer, Object>> list = new ArrayList<>();
//        sheet = wb.getSheetAt(0);
//        if (sheet != null) {
//            // 得到总行数
//            int rowNum = sheet.getLastRowNum();
//            if (rowNum > 0){
//                row = sheet.getRow(0);
//                if (row != null) {
//                    int colNum = row.getPhysicalNumberOfCells();
//                    // 正文内容应该从第二行开始,第一行为表头的标题
//                    for (int i = 0; i <= rowNum; i++) {
//                        row = sheet.getRow(i);
//                        if (row != null) {
//                            int j = 0;
//                            Map<Integer, Object> cellValue = new HashMap<>();
//                            while (j < colNum) {
//                                Cell c = row.getCell(j);
//                                Object obj = getCellFormatValue(c);
//                                cellValue.put(j, obj);
//                                j++;
//                            }
//                            list.add(cellValue);
//                        }
//                    }
//                }
//            }
//        }
//        return list;
//    }
//
//    /**
//     *
//     * @return
//     * @throws Exception
//     */
//    public List<Map<Integer, Object>> readExcelContent(int index) throws Exception {
//        if (wb == null) {
//            throw new Exception("Workbook对象为空！");
//        }
//        List<Map<Integer, Object>> list = new ArrayList<>();
//        sheet = wb.getSheetAt(index);
//        if (sheet != null) {
//            // 得到总行数
//            int rowNum = sheet.getLastRowNum();
//            if (rowNum > 0){
//                row = sheet.getRow(0);
//                if (row != null) {
//                    int colNum = row.getPhysicalNumberOfCells();
//                    // 正文内容应该从第二行开始,第一行为表头的标题
//                    for (int i = 0; i <= rowNum; i++) {
//                        row = sheet.getRow(i);
//                        if (row != null) {
//                            int j = 0;
//                            Map<Integer, Object> cellValue = new HashMap<>();
//                            while (j < colNum) {
//                                Cell c = row.getCell(j);
//                                Object obj = getCellFormatValue(c);
//                                cellValue.put(j, obj);
//                                j++;
//                            }
//                            list.add(cellValue);
//                        }
//                    }
//                }
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 根据Cell类型设置数据
//     *
//     * @param cell
//     * @return
//     * @author pangyingsheng
//     */
//    private Object getCellFormatValue(Cell cell) {
//        Object cellvalue = "";
//        if (cell != null) {
//            // 判断当前Cell的Type
//            switch (cell.getCellType()) {
//                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
//                case Cell.CELL_TYPE_FORMULA: {
//                    // 判断当前的cell是否为Date
//                    if (DateUtil.isCellDateFormatted(cell)) {
//                        // 如果是Date类型则，转化为Data格式
//                        // data格式是带时分秒的：2018-04-11 00:00:00
//                        // cellvalue = cell.getDateCellValue().toLocaleString();
//                        // data格式是不带带时分秒的：2018-04-11
//                        Date date = cell.getDateCellValue();
//                        cellvalue = date;
//                    } else {// 如果是纯数字
//
//                        // 取得当前Cell的数值
////                        cellvalue = String.valueOf(cell.getNumericCellValue());
//                        DecimalFormat df=new DecimalFormat("0");
//                        cellvalue=df.format(cell.getNumericCellValue());
//                    }
//                    break;
//                }
//                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
//                    // 取得当前的Cell字符串
//                    cellvalue = cell.getRichStringCellValue().getString();
//                    break;
//                default:// 默认的Cell值
//                    cellvalue = "";
//            }
//        } else {
//            cellvalue = "";
//        }
//        return cellvalue;
//    }
//
//}
