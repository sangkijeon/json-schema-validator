/*
 * Copyright (c) 2012, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.eel.kitchen.jsonschema.format;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.eel.kitchen.jsonschema.main.ValidationReport;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public final class CSSColorFormatSpecifierTest
{
    private static final FormatSpecifier specifier
        = CSSColorFormatSpecifier.getInstance();

    @DataProvider
    private Object[][] getData()
    {
        return new Object[][] {
            { "red", true },
            { "#ae3", true },
            { "#a41a", false },
            { "#ffffff", true },
            { "#ga3144", false },
            { "rgb(0, 15, 33)", true },
            { "rgb(0, 10)", false },
            { "rgb(-1, 10, 202)", false },
            { "rgb(256, 230, 230)", false },
        };
    }

    @Test(dataProvider = "getData")
    public void testSpeficier(final String input, final boolean valid)
    {
        final JsonNode value = JsonNodeFactory.instance.textNode(input);
        final ValidationReport report = new ValidationReport();

        specifier.checkValue(report, value);
        assertEquals(report.isSuccess(), valid, "value " + value + " should "
            + "have validated as " + valid);
    }
}
