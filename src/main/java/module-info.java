module org.openjfx {
    requires javafx.controls;
    exports org.a3b.clientCM;

    opens org.a3b.clientCM.resource;
}