import { MasterpieceWebPage } from './app.po';

describe('masterpiece-web App', function() {
  let page: MasterpieceWebPage;

  beforeEach(() => {
    page = new MasterpieceWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
