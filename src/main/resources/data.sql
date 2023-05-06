INSERT IGNORE INTO `role`(`id`, `name`) VALUES (1,'ADMIN');
INSERT IGNORE INTO `role`(`id`, `name`) VALUES (2,'MEDECIN');
INSERT IGNORE INTO `role`(`id`, `name`) VALUES (3,'GESTIONNAIRE');
INSERT IGNORE INTO `role`(`id`, `name`) VALUES (4,'PATIENT');

INSERT IGNORE INTO `user`(`nom`, `email`, `cin`, `numero`, `address`, `prenom`, `age`, `birthday`, `username`, `password`) VALUES ('MOHAMED','LAHMAR@gmail.com',435451563,'Foulen',24,null,'Foulen','toutou','$2a$10$XyXFzRcZCawnUtNNQMhS3.z4DgFbWa4vlnkVnLdIUQLbndt3a9gFy');
INSERT IGNORE INTO `user_roles`(`user_id`, `role_id`) SELECT user.id,1 FROM `user` WHERE user.username='toutou';

