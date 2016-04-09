(ns dbreader.core
  (:require [com.ashafa.clutch :as c]
            [cheshire.core :as ch] ))

(def test1 (c/get-database "test1"))

(def gt (c/get-database "grossestreffen"))

(def doc1 (c/get-document gt "e99927417d4509d627861e163db1fd3a"))



(c/put-document test1 (dissoc doc1 :_attachments))






(defn create-application-from-profile [profile-id year]
  (let [init-dok (c/put-document test1 {})
        doc-id (:_id init-dok)
        _ (c/copy-document test1 profile-id init-dok)
        new-dok (c/get-document test1 doc-id)
        _ (c/put-document test1 (assoc (select-keys new-dok [:_id :_rev :content])
                                     :type "application" :status "draft" :user (:owner new-dok) :created (str "09/04/2016") :year year))]
    doc-id))

(create-application-from-profile "e99927417d4509d627861e163db1fd3a" 2016)
