What’s Next? Music Recommendation System
========================================
Stephanie Smith, Sarah Jones, Karl Bo Lopker

Problem
-------
Music is easy to listen to, but hard to find. With so many options it is often difficult to know what song to hear next. The main problem is that a good song for one person may be another’s nails on a chalkboard. Sites like Pitchfork and Metacritic provide general music ratings. While these ratings may be a good indicator of a song’s quality, a user really wants to know if they will actually enjoy it. 
The What’s Next? Recommendation System is a website that will tell a user what song they should hear next. The recommendation is based on the user’s music tastes, not some faceless critics.
Recommendation systems are an active topic in research and industry.  They are utilized in music, movies, shopping, and many other applications to help consumers find products/services that they are interested in.  This provides benefits to both the consumer, who would like to reduce their search for interesting and relevant products, and to the businesses that are trying to sell their product.
In order for a recommendation system to provide relevant and accurate recommendations a lot of data is necessary to train the system.  Processing this much data is computationally expensive and time consuming as well as a challenging problem.  The response time of query is an important factor for an acceptable user experience, and accessing and processing that large of a quantity of data can cause a longer than tolerable delay if not implemented well.

Challenges
----------
The main challenge for this project is how to find the next song for a user using the Neighborhood Model with Map-Reduce. Secondary challenges include: how to store data, user interface, and possibly caching.  While this is not an novel contribution, this allows us to explore the complexity of a recommendation as well as to interact with Big Data and to create a simple implementation of the recommendation systems that are very influential in today’s Internet.

Solution
--------
The soul of this project is in the recommendation system. A query to the system will be a list of rated movies, represented as a user. The result of a query will be a single movie recommendation. 
For this project we will be utilizing the Yahoo! KDD music rating data with a Neighborhood Model. By calculating the adjusted cosine similarity of the items we can find the top k most similar items for each item [2].  Then we can recommend items that are similar to the music items that user rated well.  In order to work with the large KDD data set, we will use Hadoop to implement this recommendation algorithm with Map-Reduce [3].
A query in this system will be to find music matches for a user.  The recommendation system will return items that are similar to the music the user rated best.

Evaluation methods
------------------
KDD Track1 has a Training set and a Test set.  By running training the system, running the test set through it, and comparing the computed recommendation against the actual ratings we can evaluate how successful the recommendation system is.

References
----------
1. Chen, Tianqi, Zhao Zheng, Qiuxia Lu, Xiao Jiang, Yuqiang Chen, Weinang Zhan, Kailong Chen, Yong Yu, Nathan N. Liu, Bin Cao, Luheng He, and Qiang Yang. "Informative Ensemble of Multi-Resolution Dynamic Factorization Models."KDD-Cup. Yahoo! Labs, 2011. Web. 25 Apr. 2012. <http://kddcup.yahoo.com/pdf/Track1-LeBuShiShu-Paper.pdf>.[a]
3. Wu, Yao, Qiang Yan, Danny Bickson, Yucheng Low, and Qing Yang. "Efficient Multicore Collaborative Filtering." KDD-Cup. Yahoo! Labs, 2011. Web. 25 Apr. 2012. <http://kddcup.yahoo.com/pdf/Track1-LeBuShiShu-Paper.pdf>.[b]
3. Liang, Huizhi, Hogan, Jim, & Xu, Yue (2010) Parallel user proﬁling based
on folksonomy for Large Scaled Recommender Systems : an implimentation of Cascading MapReduce. In Proceedings of 10th Industrial Conference on Data Mining, IEEE, Berlin. <http://eprints.qut.edu.au/41889/1/icdm_cloudcomputing_cameraReady.pdf[c]>.

