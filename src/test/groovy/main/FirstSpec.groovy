package main

import spock.lang.Specification


class FirstSpec extends Specification {


    def "aTest"() {
        when:
        int x= 3;

        then:
        x == 3
    }

}