package com.belkatechnologies.configeditor.managers;

import com.belkatechnologies.configeditor.gui.GUI;
import com.belkatechnologies.configeditor.gui.panels.tree.OffersTree;
import com.belkatechnologies.configeditor.model.*;
import com.belkatechnologies.utils.DateUtil;
import com.belkatechnologies.utils.StringUtil;
import com.belkatechnologies.utils.TimeUtil;
import com.belkatechnologies.utils.XMLUtil;
import com.google.gson.Gson;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Author: Nikita Khvorov
 * Date: 14.03.13
 */
public class TreeManager {
    private static TreeManager ourInstance = new TreeManager();

    private OffersTree tree;
    private BORConfig borConfig;
    private File openedFile;

    public static TreeManager getInstance() {
        return ourInstance;
    }

    private TreeManager() {
    }

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
    }

    public void serializeOpenedTree() throws Exception {
        serializeTree(openedFile);
    }

    public void serializeTree(File file) throws Exception {
        serializeTree(new FileOutputStream(file));
    }

    public void serializeTree(OutputStream outputStream) throws Exception {
        getDefaultSerializer().write(borConfig, outputStream);
    }

    public void uploadTree(String url) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("config", new Gson().toJson(borConfig)));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        httpClient.execute(httpPost);
    }

    public void deserializeXML(InputStream inputStream) throws Exception {
        Serializer serializer = new Persister();
        borConfig = serializer.read(BORConfig.class, inputStream);
        rebuildPanelTree();
    }

    public void deserializeOldXML(InputStream inputStream) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        doc.normalize();
        ArrayList<EmailGroup> emails = parseEmails(doc);
        ArrayList<Application> apps = parseApps(doc);
        borConfig = new BORConfig(emails, apps);
        rebuildPanelTree();
    }

    private ArrayList<EmailGroup> parseEmails(Document doc) {
        ArrayList<EmailGroup> emails = new ArrayList<EmailGroup>();
        NodeList emailsNode = doc.getElementsByTagName("emails");
        if (emailsNode != null && emailsNode.getLength() != 0) {
            NodeList groupXMLList = XMLUtil.getNodesByName(emailsNode.item(0), "group");
            for (int i = 0; i < groupXMLList.getLength(); i++) {
                String groupName = XMLUtil.getAttribute(groupXMLList.item(i), "id");
                NodeList emailXMLList = XMLUtil.getNodesByName(groupXMLList.item(i), "email");
                ArrayList<String> groupEmails = new ArrayList<String>();
                for (int j = 0; j < emailXMLList.getLength(); j++) {
                    groupEmails.add(emailXMLList.item(j).getTextContent());
                }
                emails.add(new EmailGroup(groupName, groupEmails));
            }
        }
        return emails;
    }

    private ArrayList<Application> parseApps(Document doc) throws Exception {
        ArrayList<Application> apps = new ArrayList<Application>();
        NodeList appList = doc.getElementsByTagName("app");
        for (int j = 0; j < appList.getLength(); j++) {
            Node appNode = appList.item(j);
            String id = XMLUtil.getAttribute(appNode, "id");
            String explicitRewards = XMLUtil.getAttribute(appNode, "explicitRewards");
            String link = XMLUtil.getAttribute(appNode, "link");
            Element element = (Element) appNode;
            Node wordsNode = element.getElementsByTagName("words").item(0);
            ArrayList<RewardWord> rewardWords = parseWords(wordsNode);
            String defaultRewardValue = XMLUtil.getDataFromNode(element.getElementsByTagName("defaultRewardValue"));
            String defaultRewardType = XMLUtil.getDataFromNode(element.getElementsByTagName("defaultRewardType"));
            String oldUsersTable = XMLUtil.getDataFromNode(element.getElementsByTagName("oldUsersTable"));
            ArrayList<Offer> offerList = parseOffers((Element) appNode);
            apps.add(new Application(id, explicitRewards, link, defaultRewardValue, defaultRewardType, rewardWords, oldUsersTable, offerList));
        }
        return apps;
    }

    private ArrayList<RewardWord> parseWords(Node wordsNode) throws Exception {
        ArrayList<RewardWord> rewardWords = new ArrayList<RewardWord>();
        NodeList wordsList = ((Element) wordsNode).getElementsByTagName("word");
        for (int i = 0; i < wordsList.getLength(); i++) {
            Element word = (Element) wordsList.item(i);
            String id = XMLUtil.getAttribute(word, "id");
            String form1 = XMLUtil.getDataFromNode(word.getElementsByTagName("form" + 1));
            String form2 = XMLUtil.getDataFromNode(word.getElementsByTagName("form" + 2));
            String form3 = XMLUtil.getDataFromNode(word.getElementsByTagName("form" + 3));
            rewardWords.add(new RewardWord(id, form1, form2, form3));
        }
        return rewardWords;
    }

    private ArrayList<Offer> parseOffers(Element appNode) throws Exception {
        NodeList offersNode = appNode.getElementsByTagName("offers");
        NodeList offerXMLList = XMLUtil.getNodesByName(offersNode.item(0), "offer");
        ArrayList<Offer> offerList = new ArrayList<Offer>();
        for (int i = 0; i < offerXMLList.getLength(); i++) {
            Node node = offerXMLList.item(i);
            String id = XMLUtil.getAttribute(node, "id");
            if (id.contains(":")) {
                id = id.split(":")[1];
            }
            String incrementLevel = XMLUtil.getAttribute(node, "incrementLevel");
            String incrementLevelDateOffset = XMLUtil.getAttribute(node, "incrementLevelDateOffset");
            String minLevel = XMLUtil.getAttribute(node, "minLevel");
            String newOnly = XMLUtil.getAttribute(node, "newOnly");
            String targetURL = XMLUtil.getAttribute(node, "targetURL");
            String targetUrlFormat = XMLUtil.getAttribute(node, "targetURLFormat");
            String referalURL = XMLUtil.getAttribute(node, "referalURL");
            ArrayList<String> images = parseImages(node);
            Element element = (Element) node;
            String title = XMLUtil.getDataFromNode(element.getElementsByTagName("title"));
            String price = XMLUtil.getDataFromNode(element.getElementsByTagName("price")).replaceAll(",", ".");
            String shortDescriptions = XMLUtil.getDataFromNode(element.getElementsByTagName("shortDescriptions"));
            String offerDescription = XMLUtil.getDataFromNode(element.getElementsByTagName("offerDescription"));
            String description = offerDescription.contains("%R_TYPE%") ? offerDescription : "";
            String rewardTextOld = XMLUtil.getDataFromNode(element.getElementsByTagName("rewardText"));
            String rewardText = rewardTextOld.contains("%R_TYPE%") ? rewardTextOld : "";
            ArrayList<OfferStep> steps = parseSteps(node);
            Element targetingNode = (Element) element.getElementsByTagName("targeting").item(0);
            String sexStr = XMLUtil.getDataFromNode(targetingNode.getElementsByTagName("sex"));
            String ageStr = XMLUtil.getDataFromNode(targetingNode.getElementsByTagName("age"));
            String countryStr = XMLUtil.getDataFromNode(targetingNode.getElementsByTagName("country"));
            String cityStr = XMLUtil.getDataFromNode(targetingNode.getElementsByTagName("city"));
            String groupsStr = XMLUtil.getDataFromNode(targetingNode.getElementsByTagName("groups"));
            String idEnds = XMLUtil.getDataFromNode(targetingNode.getElementsByTagName("idEnds"));
            Targeting targeting = new Targeting(sexStr, ageStr, countryStr, cityStr, groupsStr, idEnds);
            Element checkerNode = (Element) element.getElementsByTagName("checkState").item(0);
            String checkUrl = XMLUtil.getAttribute(element.getElementsByTagName("checkState").item(0), "checkUrl");
            String statsUrl = XMLUtil.getAttribute(element.getElementsByTagName("checkState").item(0), "statsUrl");
            String strategy = XMLUtil.getDataFromNode(checkerNode.getElementsByTagName("strategy")).trim();
            String params = XMLUtil.getDataFromNode(checkerNode.getElementsByTagName("params")).trim();
            String wrappers = XMLUtil.getAttribute(element.getElementsByTagName("checkState").item(0), "wrappers");
            String seed = XMLUtil.getAttribute(element.getElementsByTagName("checkState").item(0), "seed");
            String interval = XMLUtil.getAttribute(element.getElementsByTagName("checkState").item(0), "interval");
            String maxUsers = XMLUtil.getAttribute(element.getElementsByTagName("checkState").item(0), "maxUsers");
            Checker checker = new Checker(strategy, checkUrl, statsUrl, params, wrappers, seed, interval, maxUsers);
            ArrayList<String> adminList = parseAdmins(element);
            String showLimit = XMLUtil.getDataFromNode(element.getElementsByTagName("showLimit"));
            String clickLimit = XMLUtil.getDataFromNode(element.getElementsByTagName("clickLimit"));
            String startDate1 = XMLUtil.getDataFromNode(element.getElementsByTagName("startDate"));
            Date date = DateUtil.getDate(startDate1, "dd.MM.yyyy HH:mm");
            String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
            String endDate1 = XMLUtil.getDataFromNode(element.getElementsByTagName("endDate"));
            date = DateUtil.getDate(endDate1, "dd.MM.yyyy HH:mm");
            String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
            String length = XMLUtil.getDataFromNode(element.getElementsByTagName("offerLength"));
            String sleepTime = XMLUtil.getDataFromNode(element.getElementsByTagName("sleepTime"));
            String extraParams = XMLUtil.getDataFromNode(element.getElementsByTagName("extraParams"));
            String gameSlogan = XMLUtil.getDataFromNode(element.getElementsByTagName("gameSlogan"));
            Offer offer = new Offer(id, incrementLevel, incrementLevelDateOffset, minLevel, newOnly, targetURL, targetUrlFormat, referalURL, images, title, price, shortDescriptions, description, rewardText, steps, targeting, checker, adminList, showLimit, clickLimit, startDate, endDate, length, sleepTime, extraParams, gameSlogan);
            offerList.add(offer);
        }
        return offerList;
    }

    private ArrayList<OfferStep> parseSteps(Node node) throws Exception {
        ArrayList<OfferStep> steps = new ArrayList<OfferStep>();
        String[] levels = XMLUtil.getAttribute(node, "level").split("\\|");
        int stepCount = levels.length;
        String[] rewardValues = getDataArray(XMLUtil.getAttribute(node, "rewardValue"), stepCount);
        String[] rewardTypes = getDataArray(XMLUtil.getAttribute(node, "rewardType"), stepCount);
        Element element = (Element) node;
        String[] rewardTexts = getRewardTexts(element, stepCount);
        String[] descriptions = getDescriptions(element, stepCount);
        for (int i = 0; i < stepCount; i++) {
            steps.add(new OfferStep(levels[i], descriptions[i], rewardTexts[i], rewardValues[i], rewardTypes[i]));
        }
        return steps;
    }

    private String[] getDescriptions(Element element, int steps) throws Exception {
        String line = XMLUtil.getDataFromNode(element.getElementsByTagName("offerDescription"));
        return line.contains("%R_TYPE%") ? getDataArray("", steps) : getDataArray(line, steps);
    }

    private String[] getRewardTexts(Element element, int steps) throws Exception {
        String line = XMLUtil.getDataFromNode(element.getElementsByTagName("rewardText"));
        return line.contains("%R_TYPE%") ? getDataArray("", steps) : getDataArray(line, steps);
    }

    private String[] getDataArray(String dataLine, int steps) {
        String[] dataArray;
        String fill = "";
        if ("".equals(dataLine)) {
            dataArray = new String[]{""};
        } else {
            dataArray = dataLine.split("\\|");
            fill = dataArray[0];
        }
        if (dataArray.length < steps) {
            dataArray = new String[steps];
            for (int i = 0; i < steps; i++) {
                dataArray[i] = fill;
            }
        }
        return dataArray;
    }

    private ArrayList<String> parseImages(Node node) {
        ArrayList<String> images = new ArrayList<String>();
        String image = XMLUtil.getAttribute(node, "image");
        if (StringUtil.isOkString(image)) {
            images.add(image);
        } else {
            NodeList imagesNode = ((Element) node).getElementsByTagName("images");
            NodeList imagesXMLList = XMLUtil.getNodesByName(imagesNode.item(0), "image");
            for (int i = 0; i < imagesXMLList.getLength(); i++) {
                images.add(imagesXMLList.item(i).getTextContent());
            }
        }
        return images;
    }

    private ArrayList<String> parseAdmins(Element element) throws Exception {
        ArrayList<String> adminList = new ArrayList<String>();
        String adminsLine = XMLUtil.getDataFromNode(element.getElementsByTagName("admins"));
        if (!("".equals(adminsLine))) {
            Collections.addAll(adminList, adminsLine.split(","));
        }
        return adminList;
    }

    public void refreshTree() {
        rebuildPanelTree();
    }

    private void rebuildPanelTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(borConfig);
        for (Application application : borConfig.getApps()) {
            DefaultMutableTreeNode appNode = new DefaultMutableTreeNode(application);
            for (Offer offer : application.getOffers()) {
                appNode.add(new DefaultMutableTreeNode(offer));
            }
            root.add(appNode);
        }
        tree = new OffersTree(root);
        GUI.getInstance().replaceTreePanel(tree);
    }

    private Persister getDefaultSerializer() {
        return new Persister(new Format(4));
    }

    public boolean isActive(String appId) {
        return borConfig.getAppByID(appId).isActive();
    }

    public boolean isActive(String appId, String offerId) {
        try {
            return borConfig.getAppByID(appId).getOfferByID(offerId).isActive();
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void moveOfferDown() {
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            GUI.getInstance().showAttentionMessageDialog("Select offer in the tree");
        } else if (path.getPathCount() == 2) {
            Application app = getAppFromTreePath(path);
            borConfig.moveAppDown(app);
        } else if (path.getPathCount() == 3) {
            Application app = getAppFromTreePath(path.getParentPath());
            Offer offer = getOfferFromTreePath(path);
            app.moveOfferDown(offer);
        }
        rebuildPanelTree();
    }

    public void moveOfferUp() {
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            GUI.getInstance().showAttentionMessageDialog("Select offer in the tree");
        } else if (path.getPathCount() == 2) {
            Application app = getAppFromTreePath(path);
            borConfig.moveAppUp(app);
        } else if (path.getPathCount() == 3) {
            Application app = getAppFromTreePath(path.getParentPath());
            Offer offer = getOfferFromTreePath(path);
            app.moveOfferUp(offer);
        }
        rebuildPanelTree();
    }

    public void startOffers() {
        TreePath[] treePaths = tree.getSelectionPaths();
        if (treePaths == null) {
            GUI.getInstance().showErrorMessageDialog("ERROR", "Nothing is selected");
            return;
        }
        String newDate = DateUtil.getString(System.currentTimeMillis() + TimeUtil.WEEK_TO_MS);
        for (TreePath treePath : treePaths) {
            if (treePath.getPathCount() == 2) {
                Application app = getAppFromTreePath(treePath);
                for (Offer offer : app.getOffers()) {
                    offer.setEndDate(newDate);
                }
            } else if (treePath.getPathCount() == 3) {
                Offer offer = getOfferFromTreePath(treePath);
                offer.setEndDate(newDate);
            }
        }
        rebuildPanelTree();
    }

    public void stopOffers() {
        TreePath[] treePaths = tree.getSelectionPaths();
        if (treePaths == null) {
            GUI.getInstance().showErrorMessageDialog("ERROR", "Nothing is selected");
            return;
        }
        String newDate = DateUtil.getString(System.currentTimeMillis() - TimeUtil.DAY_TO_MS);
        for (TreePath treePath : treePaths) {
            if (treePath.getPathCount() == 2) {
                Application app = getAppFromTreePath(treePath);
                for (Offer offer : app.getOffers()) {
                    offer.setEndDate(newDate);
                }
            } else if (treePath.getPathCount() == 3) {
                Offer offer = getOfferFromTreePath(treePath);
                offer.setEndDate(newDate);
            }
        }
        rebuildPanelTree();
    }

    public void deleteOffers() {
        TreePath[] treePaths = tree.getSelectionPaths();
        if (treePaths == null) {
            GUI.getInstance().showErrorMessageDialog("ERROR", "Nothing is selected");
            return;
        }
        for (TreePath treePath : treePaths) {
            if (treePath.getPathCount() == 2) {
                Application app = getAppFromTreePath(treePath);
                borConfig.getApps().remove(app);
            } else if (treePath.getPathCount() == 3) {
                Application app = getAppFromTreePath(treePath.getParentPath());
                Offer offer = getOfferFromTreePath(treePath);
                app.getOffers().remove(offer);
            }
        }
        rebuildPanelTree();
    }

    private Application getAppFromTreePath(TreePath treePath) {
        return (Application) getObjectFromTreePath(treePath);
    }

    private Offer getOfferFromTreePath(TreePath treePath) {
        return (Offer) getObjectFromTreePath(treePath);
    }

    private Object getObjectFromTreePath(TreePath treePath) {
        return ((DefaultMutableTreeNode) treePath.getLastPathComponent()).getUserObject();
    }

    public boolean contains(String appId) {
        return borConfig.contains(appId);
    }

    public boolean contains(String appId, String offerId) {
        return borConfig.contains(appId, offerId);
    }

    public List<Application> getApps() {
        return borConfig.getApps();
    }

    public void insertApp(int index, Application app) {
        borConfig.getApps().add(index, app);
        rebuildPanelTree();
    }

    public void replaceApp(Application oldApp, Application newApp) {
        Field[] fields = Application.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!fieldName.equals("offers")) {
                try {
                    Object object = PropertyUtils.getProperty(newApp, fieldName);
                    PropertyUtils.setProperty(oldApp, fieldName, object);
                } catch (IllegalAccessException ignored) {
                } catch (NoSuchMethodException ignored) {
                } catch (InvocationTargetException ignored) {
                }
            }
        }
        rebuildPanelTree();
    }

    public void replaceOffer(String appId, Offer newOffer) {
        int index = 0;
        List<Offer> offerList = borConfig.getAppByID(appId).getOffers();
        for (Offer offer : offerList) {
            if (offer.getId().equals(newOffer.getId())) {
                index = offerList.indexOf(offer);
                offerList.remove(offer);
                break;
            }
        }
        offerList.add(index, newOffer);
        rebuildPanelTree();
    }

    public void insertOffer(String appId, Offer offer) {
        borConfig.getAppByID(appId).insertOffer(offer);
    }

    public void editSelected() throws NullPointerException, CloneNotSupportedException {
        TreePath path = tree.getSelectionPath();
        switch (path.getPathCount()) {
            case 1:
                break;
            case 2:
                Application app = getAppFromTreePath(path).clone();
                GUI.getInstance().showAddAppView(app);
                break;
            case 3:
                Offer offer = getOfferFromTreePath(path);
                GUI.getInstance().showAddOfferView(offer.clone(), borConfig.getAppByOffer(offer));
                break;
        }
    }

    public Application getAppById(String appId) {
        return borConfig.getAppByID(appId);
    }

    public void copySelectedApp() throws CloneNotSupportedException {
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            GUI.getInstance().showErrorMessageDialog("Copying", "Nothing is selected.");
        } else if (path.getPathCount() != 2) {
            GUI.getInstance().showErrorMessageDialog("Copying", "The selection should be an app!");
        } else {
            Application app = getAppFromTreePath(path).clone();
            GUI.getInstance().showAddAppView(app, true);
        }
    }

    public void copySelectedOffer() throws CloneNotSupportedException {
        TreePath path = tree.getSelectionPath();
        if (path == null) {
            GUI.getInstance().showErrorMessageDialog("Copying", "Nothing is selected.");
        } else if (path.getPathCount() != 3) {
            GUI.getInstance().showErrorMessageDialog("Copying", "The selection should be an offer!");
        } else {
            Offer offer = getOfferFromTreePath(path).clone();
            GUI.getInstance().showAddOfferView(offer, true);
        }
    }
}
