(defproject meme "1.0.3-SNAPSHOT"
  :description "meme is naming tool like linux commands."
  :url "https://github.com/jiro4989/meme"
  :plugins [[lein-kibit "0.1.6"]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [criterium "0.4.4"]]
  :profiles {:uberjar {:main meme.core :aot :all}}
  :resource-paths ["src/resources"]
  :main meme.core)
