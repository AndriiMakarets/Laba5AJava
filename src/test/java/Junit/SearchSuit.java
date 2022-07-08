package Junit;

import org.junit.platform.suite.api.*;

@Suite
@SelectClasses({JunitTest.class})
@IncludeTags({"search"})
public class SearchSuit {
}
