(ns js-interop.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [js-interop.core-test]
   [js-interop.common-test]))

(enable-console-print!)

(doo-tests 'js-interop.core-test
           'js-interop.common-test)
