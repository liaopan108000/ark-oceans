package oceans.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PagebeanTool {

    public static Map<String, Object> process(PageBean<Map<String, Object>> page, String pageNo){
        Map<String, Object> map = new HashMap<>();
        if (page != null && page.getTotalRecords() > 0){
            map.put("currentPage", pageNo);
            map.put("currentRow", page.getList().size());
            map.put("dataList", page.getList());
            if ("1".equals(pageNo)){
                map.put("hasPreviousPage", false);
            } else {
                map.put("hasPreviousPage", true);
            }
            if (Integer.parseInt(pageNo) < page.getTotalPages()){
                map.put("hasNextPage", true);
            } else {
                map.put("hasNextPage", false);
            }
            map.put("totalPage", page.getTotalPages());
            map.put("totalRows", page.getTotalRecords());
        }else{
            map.put("currentPage", 0);
            map.put("currentRow", 0);
            map.put("dataList", new ArrayList<>());
            map.put("hasPreviousPage", false);
            map.put("hasNextPage", false);
            map.put("totalPage", 0);
            map.put("totalRows", 0);
        }
        return map;
    }

}
