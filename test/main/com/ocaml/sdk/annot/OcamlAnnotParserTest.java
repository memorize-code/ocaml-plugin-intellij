package com.ocaml.sdk.annot;

import com.ocaml.OCamlBaseTest;
import org.junit.Test;

@SuppressWarnings("JUnit4AnnotatedMethodInJUnit3TestCase")
public class OcamlAnnotParserTest extends OCamlBaseTest {

    private void assertParserResult(String input, String expectedOutput) {
        assertEquals(expectedOutput, OCamlAnnotParser.parse(input));
    }

    @Test
    public void testLet() {
        assertParserResult("\"test.ml\" 1 0 4 \"test.ml\" 1 0 5\n" +
                "type(\n" +
                "  int\n" +
                ")\n" +
                "ident(\n" +
                "  def x \"test.ml\" 1 0 9 \"test.ml\" 0 0 -1\n" +
                ")\n" +
                "\"test.ml\" 1 0 8 \"test.ml\" 1 0 9\n" +
                "type(\n" +
                "  int\n" +
                ")", "Va|1.4,1.5|x|int");
    }

    @Test
    public void testLetLet() {
        assertParserResult("\"test.ml\" 1 0 4 \"test.ml\" 1 0 5\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def x \"test.ml\" 2 10 10 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 8 \"test.ml\" 1 0 9\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "\"test.ml\" 2 10 14 \"test.ml\" 2 10 15\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def y \"test.ml\" 2 10 19 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 2 10 18 \"test.ml\" 2 10 19\n" +
                        "type(\n" +
                        "  int\n" +
                        ")",
                "Va|1.4,1.5|x|int\n" +
                        "Va|2.4,2.5|y|int");
    }

    @Test
    public void testLetAndLet() {
        assertParserResult("\"test.ml\" 1 0 4 \"test.ml\" 1 0 5\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def x \"test.ml\" 1 0 19 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 8 \"test.ml\" 1 0 9\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 14 \"test.ml\" 1 0 15\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def y \"test.ml\" 1 0 19 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 18 \"test.ml\" 1 0 19\n" +
                        "type(\n" +
                        "  int",
                "Va|1.4,1.5|x|int\n" +
                        "Va|1.14,1.15|y|int");
    }

    @Test
    public void testLetLetIn() {
        assertParserResult("\"test.ml\" 1 0 4 \"test.ml\" 1 0 5\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def x \"test.ml\" 3 22 30 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 2 8 16 \"test.ml\" 2 8 17\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def y \"test.ml\" 3 22 29 \"test.ml\" 3 22 30\n" +
                        ")\n" +
                        "\"test.ml\" 2 8 20 \"test.ml\" 2 8 21\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "\"test.ml\" 3 22 29 \"test.ml\" 3 22 30\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  int_ref y \"test.ml\" 2 8 16 \"test.ml\" 2 8 17\n" +
                        ")\n" +
                        "\"test.ml\" 2 8 12 \"test.ml\" 3 22 30\n" +
                        "type(\n" +
                        "  int\n" +
                        ")",
                "Va|1.4,1.5|x|int\n" +
                        "Va|2.8,2.9|y|int\n" +
                        "Id|3.7,3.8|y|y|int");
    }

    @Test
    public void testLetFunctionAndExternalCall() {
        assertParserResult("\"test.ml\" 1 0 4 \"test.ml\" 1 0 6\n" +
                        "type(\n" +
                        "  'a -> 'a -> 'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def mx \"test.ml\" 1 0 20 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 7 \"test.ml\" 1 0 8\n" +
                        "type(\n" +
                        "  'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def a \"test.ml\" 1 0 9 \"test.ml\" 1 0 20\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 9 \"test.ml\" 1 0 10\n" +
                        "type(\n" +
                        "  'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def b \"test.ml\" 1 0 13 \"test.ml\" 1 0 20\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 13 \"test.ml\" 1 0 16\n" +
                        "type(\n" +
                        "  'a -> 'a -> 'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  int_ref Stdlib.max \"stdlib.mli\" 186 7963 7963 \"stdlib.mli\" 186 7963 7987\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 17 \"test.ml\" 1 0 18\n" +
                        "type(\n" +
                        "  'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  int_ref a \"test.ml\" 1 0 7 \"test.ml\" 1 0 8\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 19 \"test.ml\" 1 0 20\n" +
                        "type(\n" +
                        "  'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  int_ref b \"test.ml\" 1 0 9 \"test.ml\" 1 0 10\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 13 \"test.ml\" 1 0 20\n" +
                        "type(\n" +
                        "  'a\n" +
                        ")",
                "Va|1.4,1.6|mx|'a -> 'a -> 'a\n" +
                        "Id|1.13,1.16|max|max|'a -> 'a -> 'a\n" +
                        "Id|1.17,1.18|a|a|'a\n" +
                        "Id|1.19,1.20|b|b|'a");
    }

    @Test
    public void testModuleWithFunctor() {
        assertParserResult("\"test.ml\" 3 15 22 \"test.ml\" 3 15 26\n" +
                        "ident(\n" +
                        "  def Make \"test.ml\" 3 15 45 \"test.ml\" 0 0 -1\n" +
                        ")",
                "Md|3.0,3.30|Make");
    }

    @Test
    public void testModule() {
        assertParserResult("\"test.ml\" 1 0 7 \"test.ml\" 1 0 11\n" +
                        "ident(\n" +
                        "  def Make \"test.ml\" 3 35 38 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 2 21 29 \"test.ml\" 2 21 30\n" +
                        "type(\n" +
                        "  int\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def x \"test.ml\" 2 21 34 \"test.ml\" 3 35 38\n" +
                        ")\n" +
                        "\"test.ml\" 2 21 33 \"test.ml\" 2 21 34\n" +
                        "type(\n" +
                        "  int\n" +
                        ")",
                "Md|1.0,3.3|Make\n" +
                        "Va|2.5,2.6|x|int");
    }

    // todo: type of the string
    @Test
    public void testUnsavedVariableAndLetFunction() {
        assertParserResult("\"test.ml\" 1 0 4 \"test.ml\" 1 0 5\n" +
                        "type(\n" +
                        "  string\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 8 \"test.ml\" 1 0 23\n" +
                        "type(\n" +
                        "  string\n" +
                        ")\n" +
                        "\"test.ml\" 2 24 32 \"test.ml\" 2 24 33\n" +
                        "type(\n" +
                        "  'a -> 'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def f \"test.ml\" 2 24 24 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 2 24 34 \"test.ml\" 2 24 35\n" +
                        "type(\n" +
                        "  'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def x \"test.ml\" 2 24 38 \"test.ml\" 2 24 39\n" +
                        ")\n" +
                        "\"test.ml\" 2 24 38 \"test.ml\" 2 24 39\n" +
                        "type(\n" +
                        "  'a\n" +
                        ")\n" +
                        "ident(\n" +
                        "  int_ref x \"test.ml\" 2 24 34 \"test.ml\" 2 24 35\n" +
                        ")",
                "Va|2.8,2.9|f|'a -> 'a\n" +
                        "Id|2.14,2.15|x|x|'a");
    }

    @Test
    public void testFunctionKeyword() {
        assertParserResult("\"test.ml\" 1 0 4 \"test.ml\" 1 0 5\n" +
                        "type(\n" +
                        "  unit -> unit\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def x \"test.ml\" 2 20 29 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 17 \"test.ml\" 1 0 19\n" +
                        "type(\n" +
                        "  unit\n" +
                        ")\n" +
                        "\"test.ml\" 2 20 22 \"test.ml\" 2 20 23\n" +
                        "type(\n" +
                        "  unit\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 17 \"test.ml\" 2 20 23\n" +
                        "type(\n" +
                        "  unit\n" +
                        ")\n" +
                        "\"test.ml\" 2 20 27 \"test.ml\" 2 20 29\n" +
                        "type(\n" +
                        "  unit\n" +
                        ")\n" +
                        "\"test.ml\" 1 0 8 \"test.ml\" 2 20 29\n" +
                        "type(\n" +
                        "  unit -> unit\n" +
                        ")",
                "Va|1.4,1.5|x|unit -> unit");
    }

    @Test
    public void testClass() {
        assertParserResult("\"test.ml\" 2 22 33 \"test.ml\" 2 22 39\n" +
                        "type(\n" +
                        "  < .. >\n" +
                        ")\n" +
                        "\"test.ml\" 3 40 70 \"test.ml\" 3 40 72\n" +
                        "type(\n" +
                        "  int list\n" +
                        ")",
                "");
    }@Test
    public void testNewObject() {
        assertParserResult("\"test.ml\" 2 22 33 \"test.ml\" 2 22 39\n" +
                        "type(\n" +
                        "  < .. >\n" +
                        ")\n" +
                        "\"test.ml\" 3 40 70 \"test.ml\" 3 40 72\n" +
                        "type(\n" +
                        "  int list\n" +
                        ")\n" +
                        "\"test.ml\" 5 93 97 \"test.ml\" 5 93 98\n" +
                        "type(\n" +
                        "  stack_of_ints\n" +
                        ")\n" +
                        "ident(\n" +
                        "  def s \"test.ml\" 5 93 118 \"test.ml\" 0 0 -1\n" +
                        ")\n" +
                        "\"test.ml\" 5 93 101 \"test.ml\" 5 93 118\n" +
                        "type(\n" +
                        "  stack_of_ints\n" +
                        ")",
                "Va|5.4,5.5|s|stack_of_ints");
    }
}
