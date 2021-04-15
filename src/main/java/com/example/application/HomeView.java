package com.example.application;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import model.User;
import org.springframework.web.client.RestTemplate;


@Route
public class HomeView extends Composite<VerticalLayout> {
    public HomeView()
    {
        VerticalLayout layout= getContent();
        VerticalLayout innerLayout= new VerticalLayout();
        TextField uuid=new TextField("ENTER THE USER UUID");
        innerLayout.setHeight("500px");
        innerLayout.setWidth("400px");
        innerLayout.getStyle().set("border", "1px solid #9E9E9E");
        layout.add(innerLayout);
        Image image = new Image("https://holos-supply.de/wp-content/uploads/2019/09/holos-supply-logo-rand.png", "DummyImage");
        image.setHeight("150px");
        image.setWidth("150px");
        innerLayout.add(image);
        H1 viewTitle=new H1("Vaadin Demo");
        viewTitle.getStyle().set("color","#877D7B");
        Button button = new Button("  RUN  ");
        innerLayout.add(viewTitle);
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_PRIMARY);
        button.getStyle().set("background-color","#877D7B");
        innerLayout.add(uuid);
        innerLayout.add(button);
        innerLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        innerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        layout.setSizeFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        button.addClickListener(event -> {
            String[] array = getData(uuid.getValue()).split(",");
            Dialog dialog = new Dialog();
            dialog.setWidth("400px");
            dialog.setHeight("120px");

            if(array.length>1) {
                User user = new User(array[1], array[0]);
                dialog.add(new Paragraph("User UUID: " + user.getUuid()),new Paragraph("User Phone Number: " + user.getPhonenumber()));
                dialog.open();
            }
            else
            {
                dialog.add(new Label(array[0]));
                dialog.open();

            }
        });

    }

    private static String getData(String uuid) // this function to call our API
    {
        final String uri = "http://localhost:8081/phonenumber/"+uuid;

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);
        String result="";
        try {
             result = restTemplate.postForObject(uri,"" ,String.class);
        }catch (RuntimeException e)
        {
             result="The user doesn't exist";
        }


        return result; //the response is text not json
    }
}
