package co.com.jscol;

import co.com.jscol.api.APIJobs;
import co.com.jscol.cli.CLIArguments;
import co.com.jscol.cli.CLIFunctions;
import co.com.jscol.model.JobPosition;
import com.beust.jcommander.JCommander;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static co.com.jscol.api.APIFunctions.buildAPI;
import static co.com.jscol.cli.CommanderFunctions.buildCommanderWithName;
import static co.com.jscol.cli.CommanderFunctions.parseArguments;

public class JobSearch {
    public static void main(String[] args) {

        JCommander jCommander = buildCommanderWithName("job-search", CLIArguments::newInstance);

        Stream<CLIArguments> streamOfCLI = parseArguments(jCommander, args, JCommander::usage)
                .orElse(Collections.emptyList())
                .stream()
                .map(obj -> (CLIArguments) obj);

        Optional<CLIArguments> cliArgumentsOptional =
                streamOfCLI.filter(cli -> !cli.isHelp())
                .filter(cli -> cli.getKeyword() != null)
                .findFirst();

        cliArgumentsOptional.map(CLIFunctions::toMap)
                .map(JobSearch::executeRequest)
                .orElse(Stream.empty())
                .forEach(System.out::println);

    }

    private static Stream<JobPosition> executeRequest(Map<String, Object> params){
        APIJobs api = buildAPI(APIJobs.class, "https://github.com");

        return Stream.of(params)
                .map(api::jobs)
                .flatMap(Collection::stream);
    }
}
