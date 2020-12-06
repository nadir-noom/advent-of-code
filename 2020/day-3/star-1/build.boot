(def project 'star-1)
(def version "0.1.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :source-paths   #{"test"}
          :dependencies   '[[org.clojure/clojure "1.8.0"]
                            [org.clojure/core.incubator "0.1.4"]
                            [metosin/bat-test "0.4.3" :scope "test"]
                            [adzerk/boot-test "RELEASE" :scope "test"]])

(task-options!
 aot {:namespace   #{'star-1.core}}
 pom {:project     project
      :version     version
      :scm         {:url "https://github.com/nadir-noom/advent-of-code"}
      :license     {"Eclipse Public License"
                    "http://www.eclipse.org/legal/epl-v10.html"}}
 repl {:init-ns    'star-1.core}
 jar {:main        'star-1.core
      :file        (str "star-1-" version "-standalone.jar")})

(require '[star-1.core :as app]
         '[clojure.java.io :as io]
         '[adzerk.boot-test :refer [test]]
         '[metosin.bat-test :refer [bat-test]])

(deftask build
  "Build the project locally as a JAR."
  [d dir PATH #{str} "the set of directories to write to (target)."]
  (let [dir (if (seq dir) dir #{"target"})]
    (comp (aot) (pom) (uber) (jar) (target :dir dir))))

(deftask run
  "Run the project."
  []
  (let [input-file (io/resource "input.txt")]
    (with-open [rdr (io/reader input-file)]
      ((resolve 'star-1.core/-main) (line-seq rdr)))))
