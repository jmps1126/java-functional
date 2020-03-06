package co.com.jscol.cli;

import java.util.HashMap;
import java.util.Map;

public class CLIFunctions {

    public static Map<String, Object> toMap(CLIArguments cliArguments){
        Map<String, Object> parmas = new HashMap<>();

        parmas.put("description", cliArguments.getKeyword());
        parmas.put("location", cliArguments.getLocation());
        parmas.put("full_time", cliArguments.isFullTime());
        parmas.put("page", cliArguments.getPage());

        if(cliArguments.isMarkdown()){
            parmas.put("markdown", true);
        }

        return parmas;
    }


}
