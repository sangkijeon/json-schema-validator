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

public final class DateFormatSpecifierTest
{
    private static final FormatSpecifier specifier
        = DateFormatSpecifier.getInstance();

    @DataProvider
    private Object[][] getData()
    {
        return new Object[][] {
            { "2012-12-02", true },
            { "20121202", false },
            { "2012/12/02", false },
            { "1922-02-30", false }
        };
    }

    @Test(dataProvider = "getData")
    public void testSpecifier(final String input, final boolean valid)
    {
        final JsonNode value = JsonNodeFactory.instance.textNode(input);
        final ValidationReport report = new ValidationReport();

        specifier.checkValue(report, value);
        assertEquals(report.isSuccess(), valid, "value " + value + " should "
            + "have validated as " + valid);
    }
}
