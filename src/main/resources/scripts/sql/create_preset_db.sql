
INSERT INTO `users` VALUES (1,'username1','$2a$12$BwNFR6MMYhqSHe7SGXPDbucG/cGVjj/aXxsQFhxdeLr0Y1PUuzx4y','email@emailunique.com','Sed vulputate purus ac tempus feugiat. Morbi malesuada ante risus, ut placerat velit convallis vitae. Ut aliquam tortor in tortor feugiat, vel congue massa pretium. ','helmet1.jpg','2016-09-30 11:22:10',1),(2,'username2','$2a$12$BwNFR6MMYhqSHe7SGXPDbucG/cGVjj/aXxsQFhxdeLr0Y1PUuzx4y','email@bgl.com','Fusce non accumsan massa. Ut molestie elementum dapibus. Nulla facilisi.','helmet1.jpg','2016-09-30 11:22:10',1),(3,'username3','$2a$12$BwNFR6MMYhqSHe7SGXPDbucG/cGVjj/aXxsQFhxdeLr0Y1PUuzx4y','email@sitea.com','Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;','helmet1.jpg','2016-09-30 11:22:10',1),(4,'username4','$2a$12$dW.yToJV/oMZbNfYtTi8NeNMW0F.cGNen7eWucJgXnGefhJwKxNBW','email@amil.com','Maecenas eget vestibulum dolor. Nullam in mollis nulla, at pellentesque orci. Quisque id eros efficitur, rhoncus sapien quis, placerat sem.','helmet1.jpg','2016-09-30 11:22:10',1),(5,'admin','$2a$12$L7UFGbRZAqCTiWaVolD9letZV2/Dv4G2QrgnlxGK7jwpixI7caefu','admin@blackhole.org','Admin user','helmet1.jpg','2016-09-30 11:22:10',1);                           

INSERT INTO `articles` VALUES (1,'Aliquam varius','international',2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc tempus mollis ante sit amet vehicula. Nam suscipit lectus eget consectetur facilisis. Duis consectetur fermentum nisl, facilisis blandit nunc pellentesque ac.','mnW1wgW.jpg','2016-09-30 09:40:18',197),(2,'Lorem ipsum dolor sit','politics',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"','p1.jpg','2016-09-30 09:33:41',20),(3,'Ipsum Lorem','politics',2,'Nullam elit tellus, laoreet nec accumsan sit amet, semper in est. Donec interdum eget odio sed mattis.','p2.jpg','2016-09-30 09:39:24',56),(4,'Nam faucibus urna','politics',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"','p3.jpg','2016-09-30 09:35:03',24),(5,'Proin id scelerisque ','politics',2,'Curabitur ultricies, magna non dictum egestas, enim turpis consequat risus, nec imperdiet quam eros et nisi. Sed at suscipit justo. Quisque mollis sed lorem a porttitor. Nam dignissim euismod malesuada.','p4.jpg','2016-09-30 09:36:05',27),(6,'Nullam elit tellus','sports',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','s1.jpg','2016-09-30 2:13:58',995),(7,'Morbi ultrices','sports',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','s2.jpg','2016-09-30 2:15:46',173),(8,'Integer mattis luctus','sports',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','s3.jpg','2016-09-30 2:16:18',43),(9,'Proin id sceler','international',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"','i2.jpg','2016-09-30 09:41:04',23),(10,'Lorem ipsum','international',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n\"There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...\"','i3.jpg','2016-09-30 09:42:34',71),(12,'Fusce risus','entertainment',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','e2.jpg','2016-09-30 2:17:13',136),(15,'Cras cursus ex','entertainment',2,'\"Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...\"\r\n','e3.jpg','2016-09-30 2:16:54',17);

INSERT INTO `comments` VALUES (1,'Sed vulputate purus ac tempus feugiat. Morbi malesuada ante risus, ut placerat velit convallis vitae. ','2016-09-30 10:32:52',1,2,3),(2,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 10:34:11',-1,1,3),(3,'\"username2 said: Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna inter','2016-09-30 10:34:56',1,1,3),(4,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 10:37:52',0,2,5),(5,'Maecenas iaculis odio non elit posuere vulputate. Phasellus ac enim sit amet lectus elementum vehicula ut vitae lacus.','2016-09-30 10:47:13',1,1,7),(6,'Aenean sed sapien in mauris malesuada','2016-09-30 10:48:18',0,3,6),(8,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et fini','2016-09-30 11:04:31',0,1,10),(9,'Maecenas eget vestibulum dolor. Nullam in mollis nulla, at pellentesque orci. Quisque id eros efficitur, rhoncus sapien quis, placerat sem.','2016-09-30 11:31:05',1,4,12),(10,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:16',6,4,1),(11,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:24',5,4,4),(12,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:33',5,4,15),(13,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:42',5,1,15),(15,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:21:55',5,4,9),(17,'Morbi ultrices arcu id mi congue efficitur. Fusce risus velit, blandit in viverra vel, eleifend et est. Duis eu massa non magna interdum iaculis. Vest','2016-09-30 13:22:18',5,1,8),(18,'Aliquam molestie porta velit, in consequat ipsum imperdiet sed.','2016-09-30 13:25:21',1,3,2),(19,'In scelerisque tellus eu auctor luctus. Donec sit amet dui non tellus convallis consequat. In commodo neque id ligula pretium vulputate. Nam sit amet ','2016-09-30 13:26:22',1,4,2),(20,'Proin ac ante quis lectus luctus tincidunt. In rutrum purus sed tincidunt aliquet. Etiam feugiat condimentum libero non ultrices. Vivamus sollicitudin','2016-09-30 13:28:10',-1,2,1);

INSERT INTO `paragraphs` VALUES (1,'Donec aliquam aliquam justo, quis rhoncus purus ultricies nec. Vivamus ut laoreet tortor. In lacinia placerat leo et accumsan. Mauris venenatis cursus tempus. Integer \r\ntempus mollis ante sit amet vehicula. Nam suscipit lectus eget consectetur facilisis. Duis consectetur fermentum nisl, facilisis blandit nunc pellentesque ac. leo lacus, rutrum luctus enim a, auctor mollis quam. Etiam porttitor erat sed tellus mattis eleifend a et velit. In vel diam purus. Phasellus ornare tincidunt metus dictum aliquet. Pellentesque pellentesque massa non nisl commodo, sit amet hendrerit felis sagittis. Proin lobortis nunc sapien, vel commodo augue placerat sit amet. Etiam finibus ipsum elit, id placerat est tristique quis. Vivamus accumsan quam vel purus vehicula, sit amet convallis elit convallis.',1,0),(2,'Quisque id mi a ligula gravida pharetra vitae ut nisi. Etiam pharetra ligula cursus dui elementum pretium. Suspendisse tempus arcu at ante dictum, id aliquet erat euismod. Proin ut pharetra magna. Proin rhoncus odio a mi sodales, nec sodales massa laoreet. Vivamus sodales diam vel elementum egestas. Sed a magna sed dolor faucibus varius et nec mauris. Nulla bibendum felis sapien, quis sodales tellus accumsan ut. Suspendisse potenti. Pellentesque maximus elit turpis, viverra egestas magna fermentum a. Integer vehicula tincidunt congue. Aenean consequat tincidunt pellentesque.',1,1),(3,'Nam ut blandit quam. Nullam felis mi, blandit nec interdum non, scelerisque eget neque. Vestibulum id porttitor leo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nunc a elit nec lectus accumsan tincidunt sodales vel augue. Pellentesque consequat facilisis nisl et interdum. Duis hendrerit nisi eu justo aliquam blandit. Ut dolor massa, rhoncus sit amet pulvinar quis, hendrerit nec nisi. Pellentesque sodales porta malesuada. In hac habitasse platea dictumst. Duis sodales sodales condimentum. Donec odio nibh, vulputate sit amet semper ac, interdum in nisi. Nulla facilisi. Integer at dolor in enim iaculis lacinia vel vel neque. Curabitur sodales, massa in viverra rutrum, nulla dui efficitur mauris, vel aliquam justo urna aliquet est. Morbi cursus, mi eget posuere placerat, tortor diam iaculis augue, et facilisis odio est tempus purus.',1,2),(4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc tempus mollis ante sit amet vehicula. Nam suscipit lectus eget consectetur facilisis. Duis consectetur fermentum nisl, facilisis blandit nunc pellentesque ac. Mauris est lacus, bibendum ac sapien sit amet, mollis luctus nisi. Curabitur sed elementum ligula. Ut vel nunc nisl. Nunc quis pulvinar felis, eget volutpat nisi. Fusce pellentesque ligula nec convallis semper. Vestibulum quis rutrum risus. Aenean nulla urna, ultrices eget pretium sit amet, commodo aliquet nunc. Suspendisse ac mauris quis massa cursus fermentum non sed arcu. Sed convallis nulla sed vestibulum eleifend. Sed malesuada risus sed porta sagittis. Nam nec ligula lacinia, fringilla nisl ac, consequat velit.',1,3),(5,'Cras volutpat mauris et nulla tempus, ac suscipit purus ultricies. In quis tristique turpis, ut tempor erat. Fusce nec turpis eu odio molestie consectetur. In hac habitasse platea dictumst. Mauris odio lacus, porttitor non sapien sit amet, efficitur pellentesque elit. Curabitur gravida dictum nibh nec ullamcorper. Proin gravida et leo vitae rhoncus.',1,4),(6,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ligula mauris, molestie nec lectus non, ultricies mollis arcu. Ut sit amet justo et tellus placerat fermentum. Quisque dictum sapien eget odio aliquam, sed gravida ipsum pellentesque. Pellentesque sit amet porta turpis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi nec ligula dignissim, pharetra odio vel, rhoncus nisi. Sed ultrices porta tempor.',2,0),(7,'Aenean dictum, neque a cursus accumsan, urna risus molestie augue, eu tincidunt est lectus et ex. Suspendisse sagittis justo eros, non viverra magna dictum vitae. Donec scelerisque est et ipsum scelerisque tristique. Ut quis lectus aliquam, porta est at, tristique augue. Phasellus eu nisi elit. Mauris dapibus, erat aliquet rutrum malesuada, nibh ligula condimentum nulla, ac euismod dolor turpis at risus. Integer vulputate orci quis nunc sagittis, eu feugiat ante porttitor. Aenean nec efficitur neque, non aliquet risus.',2,1),(8,'Pellentesque risus nunc, blandit ac neque et, fermentum venenatis lacus. Ut ac mauris posuere, consectetur ipsum eu, varius erat. Nunc in arcu risus. Fusce rhoncus enim ut lobortis mattis. Aliquam molestie porta velit, in consequat ipsum imperdiet sed. Fusce pellentesque tempus ultricies. Mauris at arcu sit amet eros molestie lobortis. Quisque commodo scelerisque erat, vel tristique tellus placerat a',2,2),(9,'Nam vitae ornare tellus. Curabitur sagittis ornare eros, eleifend cursus felis rutrum id. Suspendisse tempus ex id quam laoreet tincidunt. In tincidunt magna tortor, in varius dui aliquam eget. Vestibulum rutrum facilisis fringilla. Nullam a fermentum nulla. Integer erat odio, scelerisque eget sapien at, accumsan venenatis justo.',2,3),(10,'Praesent elementum ullamcorper urna, in malesuada ante facilisis vel. Praesent vel semper arcu. Donec accumsan mollis faucibus. In at sodales orci. Etiam nec augue in sapien pulvinar sollicitudin. Curabitur vehicula lobortis est aliquet ultrices. Sed dignissim turpis risus, ut lobortis ante gravida eget. Sed et lacus sit amet justo rutrum porta non in velit. Curabitur dictum metus sed interdum porttitor. Aliquam placerat libero eu augue lacinia dapibus. Fusce posuere elit urna. Integer sit amet neque quis nulla eleifend elementum id fermentum nisi. Nulla sed leo vitae metus porttitor suscipit pretium eu augue. Pellentesque auctor mi ut luctus mattis.',2,4),(11,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',3,0),(12,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',3,1),(13,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi lacinia placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',3,2),(14,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',3,3),(15,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',3,4),(16,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',4,0),(17,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',4,1),(18,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi lacinia placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',4,2),(19,'orem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',5,0),(20,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',5,1),(21,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',6,0),(22,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi  placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',6,1),(23,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',6,2),(24,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',7,0),(25,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',7,1),(26,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',8,0),(27,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',8,1),(28,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',8,2),(29,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',9,0),(30,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',9,1),(31,'Vivamus tellus nulla, porta vel eleifend at, interdum sit amet arcu. Cras aliquet mollis tortor. Suspendisse a purus at nulla placerat finibus vel dictum dolor. Phasellus posuere lorem et accumsan laoreet. Curabitur malesuada, libero non rhoncus eleifend, turpis ligula mattis ligula, ut feugiat lacus massa vitae neque. Aliquam ut commodo velit, id pharetra lorem. Maecenas vitae blandit massa. Suspendisse at consequat felis, ac ornare diam. Cras at purus tincidunt, sollicitudin justo eu, tempor eros. Vestibulum feugiat egestas elit, non placerat libero aliquet eu. In hac habitasse platea dictumst. Phasellus in vehicula risus. Aliquam dictum mattis magna, nec accumsan leo auctor quis. Donec et fringilla nunc. Mauris ac arcu tincidunt, auctor ex at, tincidunt libero.',10,0),(32,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',10,1),(33,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Suspendisse at laoreet mi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque augue ex, maximus at aliquam vel, placerat nec dui. Maecenas pulvinar nec diam non volutpat. Cras lobortis vulputate ex, id commodo augue suscipit quis. Cras sollicitudin interdum luctus. Donec eget auctor eros. Proin a placerat est. Integer sem dolor, hendrerit id fermentum at, mattis eget dui. Morbi nisi turpis, lacinia id ex nec, dapibus fermentum justo. Morbi pellentesque risus metus, a posuere mauris accumsan ac.',10,2),(37,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris interdum vel urna a rutrum. Vestibulum pretium lorem augue, a mollis dolor aliquet id. Suspendisse ex lectus, viverra finibus urna quis, maximus interdum nunc. Nunc molestie sapien vel mi elementum convallis. Praesent ut tellus lectus. Curabitur varius urna diam, nec ullamcorper sapien commodo in. In vitae mauris turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam a elit malesuada est dictum porttitor sed eu est. Sed arcu metus, condimentum id sagittis vitae, rutrum quis purus.',12,0),(38,'Vivamus sollicitudin scelerisque orci, sed sollicitudin nisi lacinia placerat. Nulla molestie libero at bibendum viverra. Duis euismod mi eu nibh pharetra rutrum. Sed condimentum euismod libero, id rhoncus nisi ullamcorper et. Nulla in ultricies erat, quis sollicitudin odio. Nam convallis eros vitae tortor pharetra, vitae faucibus ligula eleifend. Morbi accumsan laoreet mollis. In congue ligula nec suscipit malesuada. Ut bibendum maximus blandit. Curabitur in nibh urna. Maecenas tempor egestas porta.',12,1),(39,'Maecenas ultricies augue justo, ut congue sem pretium vel. In non tellus ac mauris tincidunt sodales. In quis turpis quis elit mattis faucibus et finibus augue. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed suscipit facilisis iaculis. Praesent lobortis ipsum id leo lobortis, vitae ornare tellus euismod. Phasellus quis sapien id tortor tincidunt ullamcorper in at massa. Integer auctor neque vel venenatis posuere. Pellentesque pulvinar blandit vehicula. Sed eu diam quam. Suspendisse tincidunt nunc non lacinia suscipit. Morbi tempor eu dui vel sagittis.',12,2),(42,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis nibh lacus, placerat a tempus eu, feugiat eu massa. Duis fermentum urna eu augue ornare, nec tincidunt felis porta. Vestibulum tellus dolor, semper sit amet luctus sollicitudin, volutpat sed felis. Quisque ornare massa quis ex tristique, eleifend iaculis leo feugiat. Nulla eu facilisis quam. Maecenas mattis tempus urna id tristique. In sed diam vehicula, commodo odio eget, sodales purus. Fusce porttitor dui sit amet nulla euismod fermentum. Integer porta mi quis urna rutrum placerat. Maecenas venenatis ex ex, eu tempor nisl tempor vitae. Nullam tortor metus, bibendum a lacinia vel, sollicitudin ac nisl. Duis nunc sapien, dignissim et quam vel, iaculis elementum libero. Donec et dui nibh',15,0),(43,'Donec volutpat augue a lobortis auctor. Vestibulum mattis aliquam elit et pretium. Integer auctor laoreet magna, quis semper turpis gravida ac. Proin malesuada ullamcorper tincidunt. Curabitur sollicitudin mattis sapien et vulputate. Phasellus a ullamcorper ligula. Proin dapibus ex id ex tristique malesuada. Sed egestas, lorem imperdiet suscipit rhoncus, arcu ligula consequat lacus, sed malesuada neque nibh vitae nibh. Morbi libero metus, efficitur ut velit congue, gravida iaculis odio. Nunc pellentesque lobortis dolor, at elementum dolor condimentum eget. Vestibulum non erat vel velit elementum tristique. Nulla accumsan magna libero. Integer id scelerisque ipsum. Integer gravida, augue eu dignissim tristique, felis ante venenatis orci, ornare fermentum eros nisl sit amet risus. Praesent maximus rhoncus urna ultrices pellentesque.',15,1);

INSERT INTO `group_members` VALUES (1,'admin',3),(2,'username2',2),(4,'username3',1),(6,'username4',1),(7,'username1',1);

INSERT INTO `acl_class` VALUES (7,'com.newssite.model.Article'),(6,'com.newssite.model.User');

INSERT INTO `acl_sid` VALUES  (1,1,'username1'),(2,1,'username2'),(3,1,'username3'),(4,1,'username4'),(5,1,'admin'),(6,1,'anonymousUser');

INSERT INTO `acl_object_identity` VALUES (1,6,1,NULL,1,0),(2,6,2,NULL,2,0),(3,6,3,NULL,3,0),(4,6,4,NULL,4,0),(5,6,5,NULL,5,0),(6,7,1,NULL,2,0),(7,7,2,NULL,2,0),(8,7,3,NULL,2,0),(9,7,4,NULL,2,0),(10,7,5,NULL,2,0),(11,7,6,NULL,2,0),(12,7,7,NULL,2,0),(13,7,8,NULL,2,0),(14,7,9,NULL,2,0),(15,7,10,NULL,2,0),(16,7,12,NULL,2,0),(17,7,15,NULL,2,0);

INSERT INTO `acl_entry` VALUES (1,1,0,1,2,1,0,0),(2,2,0,2,2,1,0,0),(3,3,0,3,2,1,0,0),(4,4,0,4,2,1,0,0),(5,5,0,5,2,1,0,0),(6,6,0,2,2,1,0,0),(7,7,0,2,2,1,0,0),(8,8,0,2,2,1,0,0),(9,9,0,2,2,1,0,0),(10,10,0,2,2,1,0,0),(11,11,0,2,2,1,0,0),(12,12,0,2,2,1,0,0),(13,13,0,2,2,1,0,0),(14,14,0,2,2,1,0,0),(15,15,0,2,2,1,0,0),(16,16,0,2,2,1,0,0),(17,17,0,2,2,1,0,0);                               

