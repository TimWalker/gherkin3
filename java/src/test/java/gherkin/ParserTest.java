package gherkin;

import gherkin.ast.DataTable;
import gherkin.ast.Feature;
import gherkin.ast.Scenario;
import gherkin.ast.Step;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void parses_simple_feature() {
        Parser<Feature> parser = new Parser<>();
        Feature feature = parser.parse("Feature: Hello");
        assertEquals("Hello", feature.getName());
    }

    @Test
    public void parses_feature_with_scenario_and_steps() {
        Parser<Feature> parser = new Parser<>();
        Feature feature = parser.parse("" +
                "Feature: Hello\n" +
                "  The Description\n" +
                "\n" +
                "  Scenario: World\n" +
                "    Given I have 4 cukes\n" +
                "      | a |\n" +
                "      | b |\n");
        assertEquals("Hello", feature.getName());

        Scenario scenario = (Scenario) feature.getScenarioDefinitions().get(0);
        assertEquals("World", scenario.getName());

        Step step = scenario.getSteps().get(0);
        assertEquals("I have 4 cukes", step.getName());

        DataTable table = (DataTable) step.getArgument();
        assertEquals("a", table.getRows().get(0).getCells().get(0).getValue());
    }
}
