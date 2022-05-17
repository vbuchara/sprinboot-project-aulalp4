package com.ifms.edu.siaula3.controllers;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ifms.edu.siaula3.enums.Pools;
import com.ifms.edu.siaula3.models.Item;
import com.ifms.edu.siaula3.repositories.ItemRepository;
import com.ifms.edu.siaula3.util.Toasts;
import com.ifms.edu.siaula3.util.Toasts.ToastTypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.validation.BindingResult;

@Controller
public class ItemsController extends BaseController{

    @Autowired
    private ItemRepository itemsRep;
    
    @RequestMapping("/items")
    public String index(Model view, HttpServletRequest request) {
        List<Item> itemsList = itemsRep.findAll(
            Sort.by(Direction.ASC, "id")
        );
        view.addAttribute("items", itemsList);

        Map<String, ?> flashAttrs = RequestContextUtils.getInputFlashMap(request);

        setupToasts(view, flashAttrs);

        return "items/index";
    }

    @GetMapping("/items/add")
    public String addView(Model view) {
        view.addAttribute("item", new Item());
        view.addAttribute("pools", Pools.values());

        return "items/add";
    }

    @PostMapping("/items/add")
    public String add(
        @Valid Item item, 
        BindingResult result, 
        Model view,
        RedirectAttributes rdAttributes
    ) {
        if(result.hasErrors()){
            view.addAttribute("item", item);
            view.addAttribute("pools", Pools.values());

            Toasts.showErrorToast(
                view, 
                "Erro ao cadastrar item!"
            );
            
            return "/items/add";
        }

        item.setCreatedAt(new Date());
        Item newItem = itemsRep.save(item);

        if(newItem == null){
            Toasts.sendToastFlashAttr(
                rdAttributes, 
                ToastTypes.ERROR, 
                "Houve alguma falha ao tentar cadastrar o item!"
            );

            return "redirect:/items";
        }

        Toasts.sendToastFlashAttr(
            rdAttributes, 
            ToastTypes.SUCCESS, 
            "Item de ID " + newItem.getId() + " cadastrado com sucesso!"
        );

        return "redirect:/items";
    }

    @GetMapping("/items/{id}")
    public String editView(
        Model view, 
        @PathVariable String id, 
        RedirectAttributes rdAttributes,
        HttpServletRequest request
    ){
        try{
            Optional<Item> item = itemsRep.findById(
                Long.valueOf(id)
            );

            if(item.isEmpty()){
                throw new EntityNotFoundException();
            }
            
            view.addAttribute("item", item.get());
            view.addAttribute("pools", Pools.values());

            return "items/edit";
        } catch(EntityNotFoundException error){
            Toasts.sendToastFlashAttr(
                rdAttributes, 
                Toasts.ToastTypes.ERROR, 
                "Item de ID " + id + " não encontrado!"
            );

            return "redirect:/items";
        } catch(Exception error) {

            return "redirect:/items";
        }
    }

    @PostMapping("/items/{id}")
    public String edit(
        Model view, 
        @PathVariable String id,
        @Valid Item item, 
        BindingResult result,
        RedirectAttributes rdAttributes
    ){
        try{
            Long itemId = Long.valueOf(id);

            if(!itemId.equals(item.getId())){
                throw new Exception("IDs da URL e do objeto editado não condizem!");
            }

            if(result.hasErrors()){
                throw new Exception("Falha ao editar item de ID " + id + "!");
            }

            Item updatedItem = itemsRep.save(item);

            if(updatedItem == null){
                throw new Exception(
                    "Houve algum problema ao tentar salvar o item de ID " + id + "!"
                );
            }

            Toasts.sendToastFlashAttr(
                rdAttributes, 
                ToastTypes.SUCCESS, 
                "Item de ID " + id + " editado com sucesso!"
            );

            return "redirect:/items";
        } catch(NumberFormatException error){
            Toasts.showErrorToast(
                view, 
                "ID informado de maneira incorreta na URL!"
            );
        } catch(Exception error){
            Toasts.showErrorToast(view, error.getMessage());
        }

        view.addAttribute("item", item);
        view.addAttribute("pools", Pools.values());

        return "/items/edit";
    }

    @DeleteMapping("/items/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable String id){
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();

        try {
            itemsRep.deleteById(Long.valueOf(id));
            response.put("mensagem", "Item de ID " + id + " deletado com sucesso.");
        } catch(Exception e) {
            response.put("mensagem", "Erro ao deletar item.");
        }

        return response;
    }

    private void setupToasts(Model view, Map<String, ?> flashAttrs){
        if(flashAttrs != null){
            String errorMsg = (String) flashAttrs.get(ToastTypes.ERROR.toString());
            String successMsg = (String) flashAttrs.get(ToastTypes.SUCCESS.toString());

            if(errorMsg != null){
                Toasts.showErrorToast(view, errorMsg);
            }

            if(successMsg != null){
                Toasts.showSuccessToast(view, successMsg);
            }
        }
    }
}
